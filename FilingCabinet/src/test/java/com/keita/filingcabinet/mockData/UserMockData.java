package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.dto.AuthRequest;
import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.model.enums.Role;

import java.util.Arrays;
import java.util.List;

public abstract class UserMockData {

    public static User getUser() {
        return User.builder()
                .id("616614a5c650ebeecd24b0b5")
                .email("employee1@gmail.com")
                .password("employee")
                .role(Role.USER)
                .isAccountVerified(true)
                .isActive(true)
                .build();
    }

    public static List<User> getUsers() {
        return Arrays.asList(
                User.builder()
                        .id("616614a5c650ebeecd24b0b5")
                        .email("employee1@gmail.com")
                        .password("employee")
                        .role(Role.USER)
                        .isAccountVerified(true)
                        .isActive(true)
                        .build(),
                User.builder()
                        .id("61678c57c1279146566ab25d")
                        .email("employee2@gmail.com")
                        .password("employee")
                        .role(Role.USER)
                        .isAccountVerified(true)
                        .isActive(true)
                        .build(),
                User.builder()
                        .id("61678c72c6872ed812a82b6a")
                        .email("employee3@gmail.com")
                        .password("employee")
                        .role(Role.USER)
                        .isAccountVerified(false)
                        .isActive(true)
                        .build()
        );
    }

    public static AuthRequest getAuthRequest() {
        return AuthRequest.builder()
                .email("employee1@gmail.com")
                .password("employee")
                .build();
    }

    public static AuthRequest getAuthRequestNonExistent() {
        return AuthRequest.builder()
                .email("non_existent@gmail.com")
                .password("employee")
                .build();
    }
}
