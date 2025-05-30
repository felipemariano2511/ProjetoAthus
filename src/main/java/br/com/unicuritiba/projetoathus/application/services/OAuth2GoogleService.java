package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class OAuth2GoogleService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    public ResponseEntity<?> validarToken(String accessToken) {

        Map<String, Object> usuarioInfo = getUserInfo(accessToken);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail((String) usuarioInfo.get("email"));

        if (usuarioOptional.isEmpty()) {

            Usuario usuario = new Usuario();

            usuario.setNomeCompleto((String) usuarioInfo.get("name"));
            usuario.setNome((String) usuarioInfo.get("name"));
            usuario.setEmail((String) usuarioInfo.get("email"));
            usuario.setSenha(passwordEncoder.encode(senhaRandom()));
            usuario.setNumero(0);
            usuario.setApartamento(0);
            usuario.setImagemPerfil((String) usuarioInfo.get("picture"));
            usuario.setImagemPerfil("../images/usuario.png");
            usuario.setNivel((short) 0);
            usuario.setAtivo(true);
            usuario.setBanido(false);

            usuarioRepository.save(usuario);

            Map<String, Object> variaveis = new HashMap<>();
            variaveis.put("nome", usuario.getNomeCompleto());
            variaveis.put("email", usuario.getEmail());
            emailService.enviarEmailComTemplate(
                    usuario.getEmail(),
                    "Cadastro realizado com sucesso",
                    "email-conta-criada",
                    variaveis
            );
            String novoAccessToken = tokenService.gerarAccessToken(usuario.getEmail(), usuario.getNivel());
            String novoRefreshToken = tokenService.gerarRefreshToken(usuario.getEmail(), usuario.getNivel());

            return ResponseEntity.ok(Map.of(
                    "accessToken", novoAccessToken,
                    "refreshToken", novoRefreshToken
            ));

        } else {

            String novoAccessToken = tokenService.gerarAccessToken((String) usuarioInfo.get("email"), usuarioOptional.get().getNivel());
            String novoRefreshToken = tokenService.gerarRefreshToken((String) usuarioInfo.get("email"), usuarioOptional.get().getNivel());

            return ResponseEntity.ok(Map.of(
                    "accessToken", novoAccessToken,
                    "refreshToken", novoRefreshToken
            ));
        }
    }

    private String senhaRandom() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String especiais = "!@#$%&*";
        String todos = letras + numeros + especiais;

        StringBuilder senha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            senha.append(todos.charAt(random.nextInt(todos.length())));
        }

        return senha.toString();
    }
 
    private Map getUserInfo(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                request,
                Map.class
        );

        return response.getBody();
    }
}