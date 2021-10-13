package com.keita.filingcabinet.model.entity;

import com.keita.filingcabinet.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User implements Serializable {

    @Id
    private String id;
    private String email,password;
    private Role role;
    private Boolean isActive;
    private Map<String,String> registerBy;
}
