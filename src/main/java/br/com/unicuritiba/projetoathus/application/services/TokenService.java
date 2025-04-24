package br.com.unicuritiba.projetoathus.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import static com.auth0.jwt.JWT.require;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private DecodedJWT jwt;

    public String gerarAccessToken(String email) throws IllegalStateException{

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(email)
                    .withClaim("type", "access-token")
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);
    }


    public DecodedJWT validarToken(String token) throws IllegalStateException{
            return require(Algorithm.HMAC256(secret))
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token);
    }

    public String gerarRefreshToken(String email) throws IllegalStateException{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(email)
                    .withClaim("type", "refresh-token")
                    .withExpiresAt(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.of("-3")))
                    .sign(algorithm);

    }

    public String gerarRefreshToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(email)
                    .withClaim("type", "refresh")
                    .withExpiresAt(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.of("-3")))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar refresh token.");
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-3"));
    }
}
