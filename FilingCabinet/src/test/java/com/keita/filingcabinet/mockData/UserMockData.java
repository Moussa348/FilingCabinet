package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.entity.User;
import com.keita.filingcabinet.model.enums.Role;

public abstract class UserMockData {

    public static User getUser(){
        return User.builder()
                .id("616614a5c650ebeecd24b0b5")
                .email("employee1@gmail.com")
                .password("employee")
                .role(Role.USER)
                .build();
    }
}
