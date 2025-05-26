package br.com.unicuritiba.projetoathus.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ResetSenhaToken {
    private final String token;
    private final String email;
    private final Instant expiraEm;

    public boolean expirado() {
        return Instant.now().isAfter(expiraEm);
    }
}
