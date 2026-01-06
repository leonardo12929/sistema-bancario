package com.grupo7.Sistema.bancario.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.grupo7.Sistema.bancario.usuario.Usuario;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String gerarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
            .withIssuer("Cliente_API")
            .withSubject(usuario.getLogin())
            .withExpiresAt(dataExpiracao())
            .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token", ex);
        }
    }

    public String getSubject(String TokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("Cliente_API")
            .build()
            .verify(TokenJWT)
            .getSubject();
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro do token");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
