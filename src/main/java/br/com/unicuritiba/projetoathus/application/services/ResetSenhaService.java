package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.infrastructure.security.ResetSenhaToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ResetSenhaService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, ResetSenhaToken> tokens = new ConcurrentHashMap<>();

    public ResponseEntity<?> enviarTokenReset(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        String token = tokenService.gerarResetToken(usuario.getEmail());
        Instant expiraEm = Instant.now().plusSeconds(900);

        ResetSenhaToken resetToken = new ResetSenhaToken(token, email, expiraEm);
        tokens.put(token, resetToken);
        String urlBase = "http://localhost:8080/auth/reset-senha/confirmar";
        String linkReset = urlBase + "?token=" + token;

        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("nome", usuario.getNome());
        variaveis.put("linkReset", linkReset);

        emailService.enviarEmailComReset(
                usuario.getEmail(),
                "Redefinição de senha - Instituto Athus",
                "email-reset-senha",
                variaveis
        );

        return ResponseEntity.ok("Token de redefinição enviado com sucesso.");
    }

    public ResponseEntity<?> resetarSenha(String token, String novaSenha) {
        ResetSenhaToken reset = tokens.get(token);

        if (reset == null || reset.expirado()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Token inválido ou expirado"));
        }

        Usuario usuario = usuarioRepository.findByEmail(reset.getEmail())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
        tokens.remove(token);

        return ResponseEntity.ok(Map.of("message", "Senha redefinida com sucesso"));
    }
}
