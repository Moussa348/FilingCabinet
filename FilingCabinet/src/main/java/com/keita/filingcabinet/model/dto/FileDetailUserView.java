package com.keita.filingcabinet.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keita.filingcabinet.model.enums.Role;
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
public class FileDetailUserView implements Serializable {

    public String id,filename,description;
    private Map<String, String> uploadBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime uploadDate;
}
