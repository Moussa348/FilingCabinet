package com.keita.filingcabinet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("patients")
public class Patient {

    @Id
    private String id;
    private String firstName,lastName,email,phoneNumber,address,socialNumber;
}
