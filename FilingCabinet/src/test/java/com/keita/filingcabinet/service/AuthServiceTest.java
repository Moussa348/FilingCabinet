package com.keita.filingcabinet.service;

import com.keita.filingcabinet.exception.WrongCredentialsException;
import com.keita.filingcabinet.mockData.UserMockData;
import com.keita.filingcabinet.model.dto.AuthRequest;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    JwtService jwtService;

    @Mock
    UserService userService;

    @InjectMocks
    AuthService authService;

    @Test
    void shouldLogin() throws WrongCredentialsException {
        //ARRANGE
        AuthRequest authRequest = UserMockData.getAuthRequest();
        User user = UserMockData.getUser();

        when(userService.findUserByEmailAndPassword(anyString(),anyString())).thenReturn(user);
        when(jwtService.generate(user)).thenReturn("random_token");

        //ACT
        String token = authService.login(authRequest);

        //ASSERT
        assertNotNull(token);
    }

    @Test
    void shouldNotLogin() throws WrongCredentialsException {
        //ARRANGE
        when(userService.findUserByEmailAndPassword(anyString(),anyString())).thenThrow(WrongCredentialsException.class);

        //ASSERT
        assertThrows(WrongCredentialsException.class, () -> authService.login(UserMockData.getAuthRequest()));
    }
}
