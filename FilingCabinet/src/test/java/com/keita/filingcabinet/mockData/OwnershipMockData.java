package com.keita.filingcabinet.mockData;

import com.keita.filingcabinet.model.enums.Role;

import java.util.Collections;
import java.util.Map;

public abstract class OwnershipMockData {

    public static Map<String,String> getUserDetails(){
        return Collections.singletonMap(Role.SUDO.toString(),"employee1@gmail.com");
    }
}
