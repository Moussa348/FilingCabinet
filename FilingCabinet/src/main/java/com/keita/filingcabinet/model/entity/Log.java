package com.keita.filingcabinet.model.entity;

import com.keita.filingcabinet.model.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Document("logs")
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {

    @Id
    private String id;
    private String fileId;
    private OperationType operationType;
    private LocalDateTime visualizedDate;
    private String visualizedBy;
}
