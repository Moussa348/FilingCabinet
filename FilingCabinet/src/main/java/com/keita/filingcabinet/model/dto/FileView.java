package com.keita.filingcabinet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileView implements Serializable {

    public String id,filename,description,uploadBy;
}
