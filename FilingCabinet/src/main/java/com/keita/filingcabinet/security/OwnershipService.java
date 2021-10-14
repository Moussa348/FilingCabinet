package com.keita.filingcabinet.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Map;

public abstract class OwnershipService {

    public static Map<String,String> getCurrentUserDetails(){
        DecodedJWT decodedJWT = ((DecodedJWT)SecurityContextHolder
                .getContext().getAuthentication().getDetails());

        return Collections.singletonMap(decodedJWT.getClaim(JwtService.USER_ROLE_CLAIM).asString(),
                decodedJWT.getClaim(JwtService.USER_EMAIL_CLAIM).asString());
    }
}
