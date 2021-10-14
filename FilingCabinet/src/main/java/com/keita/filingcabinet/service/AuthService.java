package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.WrongCredentialsException;
import com.keita.filingcabinet.model.dto.AuthRequest;
import com.keita.filingcabinet.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    public String login(AuthRequest authRequest) throws WrongCredentialsException {
        return jwtService.generate(userService.findUserByEmailAndPassword(authRequest.getEmail(),authRequest.getPassword()));
    }
}
