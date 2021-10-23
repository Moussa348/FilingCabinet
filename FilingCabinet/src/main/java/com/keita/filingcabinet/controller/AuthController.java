package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.exception.WrongCredentialsException;
import com.keita.filingcabinet.model.dto.AuthRequest;
import com.keita.filingcabinet.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) throws WrongCredentialsException {
        return authService.login(authRequest);
    }

}
