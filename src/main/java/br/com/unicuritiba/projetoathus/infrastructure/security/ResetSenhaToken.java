package br.com.unicuritiba.projetoathus.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResetSenhaToken {
    private final String token;
    private final String email;
    private final LocalDateTime expiraEm;

    public boolean expirado() {
        return LocalDateTime.now().isAfter(expiraEm);
    }
}
