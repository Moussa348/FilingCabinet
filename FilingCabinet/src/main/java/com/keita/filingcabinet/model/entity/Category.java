package com.keita.filingcabinet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("categories")
public class Category implements Serializable {

    @Id
    private String id;
    private String name;
    private Boolean isActive;
}
