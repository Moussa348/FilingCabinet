package com.keita.filingcabinet.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailSuperUserView implements Serializable {

    private String id, name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    private Map<String, String> createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Map<String, LocalDateTime> deactivatedBy;
}
