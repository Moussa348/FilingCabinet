package com.keita.filingcabinet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.keita.filingcabinet.mockData.UserMockData;
import com.keita.filingcabinet.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.keita.filingcabinet.security.JwtService.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtServiceTest {

    JwtService jwtService;

    @BeforeEach
    void init() {
        jwtService = new JwtService(10L, "asdasd");
    }

    @Test
    void generate() {
        //ARRANGE
        User user = UserMockData.getUser();

        //ACT
        String token = jwtService.generate(user);

        //ASSERT
        assertEquals(user.getId(), jwtService.getJwtVerifier().verify(token).getSubject());
    }

    @Test
    void shouldVerify() {
        //ARRANGE
        User user = UserMockData.getUser();
        String token = "Bearer " + JWT.create().withSubject(user.getId().toString())
                .withClaim(USER_ID_CLAIM, user.getId())
                .withClaim(USER_EMAIL_CLAIM, user.getEmail())
                .withClaim(USER_ROLE_CLAIM, user.getRole().toString())
                .sign(jwtService.getAlgorithm());

        //ACT
        DecodedJWT decodedJWT = jwtService.verify(token);

        //ASSERT
        assertEquals(user.getId(), decodedJWT.getSubject());
        assertEquals(user.getEmail(), decodedJWT.getClaim(USER_EMAIL_CLAIM).asString());
        assertEquals(user.getRole().toString(), decodedJWT.getClaim(USER_ROLE_CLAIM).asString());
    }

    @Test
    void shouldNotVerify() {
        //ARRANGE
        String token = "asdadsasdasdads";

        //ASSERT
        assertThrows(JWTDecodeException.class, () -> jwtService.verify(token));
        assertThrows(JWTVerificationException.class, () -> jwtService.verify(token));
    }

}
