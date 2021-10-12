package com.keita.filingcabinet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("categories")
public class Category implements Serializable {

    @Id
    private String id;
    private String name;
    private Map<String, String> createdBy;
    private Map<String, String> deactivatedBy;
    private LocalDateTime creationDate,deactivationDate;
    private Boolean isActive;
}
