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
@Document("fs.files")
public class File implements Serializable {

    @Id
    private String id;
    private String folderId;
    private Map<String, String> uploadBy;
    private String fileName, description;
    private Boolean isActive;
    private LocalDateTime deactivationDate;

}
