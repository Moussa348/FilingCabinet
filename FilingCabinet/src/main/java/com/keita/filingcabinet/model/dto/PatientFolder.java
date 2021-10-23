package com.keita.filingcabinet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientFolder implements Serializable {

    private String patientId,patientName;
    private Map<String,String> mapFolders;
}
