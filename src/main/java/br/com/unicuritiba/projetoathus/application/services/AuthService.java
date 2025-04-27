package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.application.mails.VerificarCadastro;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.dto.LoginRequestDTO;
import br.com.unicuritiba.projetoathus.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String iniciarCadastro(RegisterRequestDTO body) {
        if (repository.findByEmail(body.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        VerificarCadastro verificador = verificacoes.getOrDefault(body.email(), new VerificarCadastro());
        int codigo = verificador.gerarNovoCodigo();

        verificacoes.put(body.email(), verificador);
        cadastrosPendentes.put(body.email(), body);

        emailService.enviarEmail(
                body.email(),
                "Verificação de e-mail",
                String.format("Olá, %s! Seu código de verificação é: %d", body.nome(), codigo)
        );

        return "Código de verificação enviado para o e-mail.";
    }

    public ResponseEntity<Object> login(LoginRequestDTO body) {
        Usuario usuario = repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(body.senha(), usuario.getSenha())) {
            return ResponseEntity.badRequest().body("Senha inválida.");
        }

        String accessToken = tokenService.gerarToken(usuario.getEmail());
        String refreshToken = tokenService.gerarRefreshToken(usuario.getEmail());

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    public ResponseEntity<?> refresh(String refreshToken) {
        String email = tokenService.validarToken(refreshToken);

        if (email == null) {
            return ResponseEntity.badRequest().body("Refresh token inválido.");
        }

        String novoAccessToken = tokenService.gerarToken(email);
        String novoRefreshToken = tokenService.gerarRefreshToken(email);

        return ResponseEntity.ok(Map.of(
                "accessToken", novoAccessToken,
                "refreshToken", novoRefreshToken
        ));
    }

    public ResponseEntity<?> validarCodigo(String email, int codigo) {
        VerificarCadastro verificador = verificacoes.get(email);
        if (verificador == null) {
            throw new IllegalStateException("Nenhum código foi gerado para esse e-mail.");
        }

        if (!verificador.verificarCodigo(codigo)) {
            if (verificador.getTentativasRestantes() <= 0) {
                throw new IllegalStateException("Tentativas excedidas. Aguarde 60 minutos.");
            }
            throw new IllegalArgumentException("Código inválido. Tentativas restantes: " + verificador.getTentativasRestantes());
        }

        RegisterRequestDTO dados = cadastrosPendentes.get(email);
        if (dados == null) {
            throw new IllegalStateException("Nenhum cadastro pendente foi encontrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setNumero(0);
        usuario.setApartamento(0);
        usuario.setImagemPerfil("../images/usuario.png");
        usuario.setNivel((short) 0);
        usuario.setAtivo(true);
        usuario.setBanido(false);

        repository.save(usuario);
        verificacoes.remove(email);
        cadastrosPendentes.remove(email);

        emailService.enviarEmail(email, "Cadastro concluído", "Parabéns, sua conta foi criada com sucesso!");

        String novoAccessToken = tokenService.gerarToken(usuario.getEmail());
        String novoRefreshToken = tokenService.gerarRefreshToken(email);

        return ResponseEntity.ok(Map.of(
                "accessToken", novoAccessToken,
                "refreshToken", novoRefreshToken
        ));
    }
}
