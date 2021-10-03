package com.keita.filingcabinet.model.entity;

import com.keita.filingcabinet.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("persons")
public class Person implements Serializable {

    @Id
    private String id;
    private String firstName,lastName,email,phoneNumber,address;
    private List<Role> roles;
    private Boolean isActive;
}
