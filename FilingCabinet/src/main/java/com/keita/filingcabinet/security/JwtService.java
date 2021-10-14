package com.keita.filingcabinet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.keita.filingcabinet.model.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Data
@Service
public class JwtService {

    private final long duration;
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;

    public static String USER_ROLE_CLAIM = "role";
    public static String USER_ID_CLAIM = "userId";
    public static String USER_EMAIL_CLAIM = "userEmail";

    public JwtService(@Value("${security.jwt.duration}") Long duration,
                      @Value("${security.jwt.seed}") String seed) {
        this.algorithm = Algorithm.HMAC256(SecureRandom.getSeed(16));
        this.jwtVerifier = JWT.require(algorithm).build();
        this.duration = TimeUnit.MINUTES.toMillis(duration);
    }

    public String generate(User user) {
        final long time = System.currentTimeMillis();

        return JWT.create()
                .withSubject(user.getId())
                .withClaim(USER_EMAIL_CLAIM, user.getEmail())
                .withClaim(USER_ROLE_CLAIM, user.getRole().toString())
                .withIssuedAt(new Date(time))
                .withExpiresAt(new Date(time + duration))
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) throws JWTVerificationException, JWTDecodeException {
        if (token == null)
            throw new JWTVerificationException("Token cannot be null,");

        if (token.startsWith("Bearer "))
            token = token.replace("Bearer ", "");

        else
            throw new JWTDecodeException("Can't decode");

        return jwtVerifier.verify(token);
    }

}
