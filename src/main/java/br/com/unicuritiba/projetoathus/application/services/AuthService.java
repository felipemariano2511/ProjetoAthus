package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.infrastructure.mails.VerificarCadastro;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.domain.dto.auth.LoginRequestDTO;
import br.com.unicuritiba.projetoathus.domain.dto.auth.RegisterRequestDTO;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TokenService tokenService;

    private final Map<String, VerificarCadastro> verificacoes = new ConcurrentHashMap<>();
    private final Map<String, RegisterRequestDTO> cadastrosPendentes = new ConcurrentHashMap<>();

    public ResponseEntity<Map<String, String>> iniciarCadastro(RegisterRequestDTO body) {
        try{
            if (repository.findByEmail(body.email()).isPresent()) {
                throw new ConflictException("Não é possível cadastrar o usuário, pois o e-mail já está em uso.");
            }

            VerificarCadastro verificador = verificacoes.getOrDefault(body.email(), new VerificarCadastro());
            int codigo = verificador.gerarCodigo(false);

            verificacoes.put(body.email(), verificador);
            cadastrosPendentes.put(body.email(), body);

            Map<String, Object> variaveis = new HashMap<>();
            variaveis.put("nome", body.nomeCompleto());
            variaveis.put("codigo", codigo);

            emailService.enviarEmailComTemplate(
                    body.email(),
                    "Seu código de verificação - Instituto Athus",
                    "email-codigo-verificacao",
                    variaveis
            );


            return ResponseEntity.ok(Map.of(
                    "message",   String.format("Código de verificação enviado para o e-mail: %s", body.email())
            ));
        }catch (Exception e){
            throw new BadRequestException("Todos os campos não foram preenchidos");
        }
    }

    public ResponseEntity<Map<String, String>> login(LoginRequestDTO body) {

        return repository.findByEmail(body.email())
                .map(usuario -> {
                    if (!passwordEncoder.matches(body.senha(), usuario.getSenha())) {
                        throw new BadRequestException("Senha incorreta!");
                    }

                    String accessToken = tokenService.gerarAccessToken(usuario.getEmail(), usuario.getNivel());
                    String refreshToken = tokenService.gerarRefreshToken(usuario.getEmail(), usuario.getNivel());

                    return ResponseEntity.ok(Map.of(
                            "accessToken", accessToken,
                            "refreshToken", refreshToken
                    ));
                })
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    public ResponseEntity<Map<String, String>> refresh(String refreshToken) {
        DecodedJWT jwt = tokenService.validarToken(refreshToken);

        if (jwt == null) {
            throw new BadRequestException("RefreshToken inválido!");
        }

        if (jwt.getClaim("type").asString().equals("refresh-token")) {

            String novoAccessToken = tokenService.gerarAccessToken(jwt.getSubject(), jwt.getClaim("nivel").asInt());

            return ResponseEntity.ok(Map.of(
                    "accessToken", novoAccessToken
            ));
        } else{
            throw new BuisnessException("Não é possível recarregar o token utilizando um accessToken!");
        }
    }

    public ResponseEntity<Map<String, String>> solicitarNovoCodigo(RegisterRequestDTO body) {
        VerificarCadastro verificador = verificacoes.getOrDefault(body.email(), new VerificarCadastro());
        int codigo = verificador.gerarCodigo(true);

        verificacoes.put(body.email(), verificador);
        cadastrosPendentes.put(body.email(), body);

        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("nome", body.nomeCompleto());
        variaveis.put("codigo", codigo);

        emailService.enviarEmailComTemplate(
                body.email(),
                "Seu código de verificação - Instituto Athus",
                "email-codigo-verificacao",
                variaveis
        );


        return ResponseEntity.ok(Map.of(
                "message",   String.format("Código de verificação enviado para o e-mail: %s", body.email())
        ));
    }

    public ResponseEntity<Map<String, String>> validarCodigo(String email, int codigo) {
        try {
            VerificarCadastro verificador = verificacoes.get(email);
            if (verificador == null) {
                throw new BuisnessException("Nenhum código foi gerado para esse e-mail.");
            }

            var resultado = verificador.verificarCodigo(codigo);

            if (!resultado.sucesso()) {
                throw new BadRequestException(resultado.mensagem());
            }

            RegisterRequestDTO dados = cadastrosPendentes.get(email);
            if (dados == null) {
                throw new IllegalStateException("Nenhum cadastro pendente foi encontrado.");
            }

            Usuario usuario = new Usuario();
            usuario.setNomeCompleto(dados.nomeCompleto());
            usuario.setNome(dados.nomeCompleto());
            usuario.setEmail(dados.email());
            usuario.setSenha(passwordEncoder.encode(dados.senha()));
            usuario.setNumero(0);
            usuario.setApartamento(0);
            usuario.setImagemPerfil("storage/imagens/usuarios/usuario.png");
            usuario.setNivel((short) 0);
            usuario.setAtivo(true);
            usuario.setBanido(false);

            repository.save(usuario);
            verificacoes.remove(email);
            cadastrosPendentes.remove(email);

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
            String novoRefreshToken = tokenService.gerarRefreshToken(email, usuario.getNivel());

            return ResponseEntity.ok(Map.of(
                    "accessToken", novoAccessToken,
                    "refreshToken", novoRefreshToken
            ));
        }catch (Exception e){
            throw new BadRequestException("Campos não preenchidos.");
        }
    }
}
