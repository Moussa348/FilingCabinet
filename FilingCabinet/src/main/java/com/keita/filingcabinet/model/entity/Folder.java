package com.keita.filingcabinet.model.entity;

import com.keita.filingcabinet.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("folders")
public class Folder implements Serializable {

    @Id
    private String id;
    private String personId;
    private String categoryName;
    private LocalDateTime creationDate;
    private Boolean isActive;
    private List<Role> allowedRoles;
}
