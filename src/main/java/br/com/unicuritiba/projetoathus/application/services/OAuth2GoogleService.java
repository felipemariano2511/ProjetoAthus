package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class OAuth2GoogleService {

    private final Short NIVEL = 0;
    private final Boolean ATIVO = true;
    private final Boolean NAO_BANIDO = true;

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

    public String validarToken(String accessToken) {

        Map<String, Object> usuarioInfo = getUserInfo(accessToken);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail((String) usuarioInfo.get("email"));

        if (usuarioOptional.isEmpty()) {

            Usuario usuario = new Usuario();

            usuario.setNome((String) usuarioInfo.get("name"));
            usuario.setEmail((String) usuarioInfo.get("email"));
            usuario.setSenha(passwordEncoder.encode(senhaRandom()));
            usuario.setNumero(0);
            usuario.setApartamento(0);
            usuario.setImagemPerfil((String) usuarioInfo.get("picture"));
            usuario.setNivel(NIVEL);
            usuario.setAtivo(ATIVO);
            usuario.setBanido(NAO_BANIDO);

            usuarioRepository.save(usuario);
            emailService.enviarEmail((String) usuarioInfo.get("email"), "Cadastro concluído", "Parabéns, sua conta foi criada com sucesso!");

            return tokenService.gerarToken((String) usuarioInfo.get("email"));

        } else {

            return tokenService.gerarToken((String) usuarioInfo.get("email"));
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

    public Map<String, Object> getUserInfo(String accessToken) {

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