package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnauthorizedException;
import br.com.unicuritiba.projetoathus.infrastructure.security.ResetSenhaToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResetSenhaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Map<String, ResetSenhaToken> tokens = new ConcurrentHashMap<>();

    public ResponseEntity<Map> enviarTokenReset(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        String token = tokenService.gerarResetToken(usuario.getEmail());
        LocalDateTime expiraEm = LocalDateTime.now().plusSeconds(900);

        ResetSenhaToken resetToken = new ResetSenhaToken(token, email, expiraEm);
        tokens.put(token, resetToken);
        String urlBase = "http://localhost:8080/auth/resetsenha/confirmar";
        String linkReset = urlBase + "?token=" + token;

        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("nome", usuario.getNome());
        variaveis.put("linkReset", linkReset);

        emailService.enviarEmailComReset(
                usuario.getEmail(),
                "Redefinir senha - Instituto Athus",
                "email-reset-senha",
                variaveis
        );

        return ResponseEntity.ok(Map.of("message", "Solicitação para redefinir a senha enviado com sucesso!" +
                                                            " Por favor, verifique seu email e siga as etapas."));
    }

    public ResponseEntity<?> resetarSenha(String token, String novaSenha) {
        ResetSenhaToken reset = tokens.get(token);

        if (reset == null || reset.expirado()) {
            throw new UnauthorizedException("Token inválido ou expirado.");
        }

        Usuario usuario = usuarioRepository.findByEmail(reset.getEmail())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
        tokens.remove(token);

        return ResponseEntity.ok(Map.of("message", "Senha redefinida com sucesso."));
    }
}
