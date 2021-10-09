package com.keita.filingcabinet.model.dto;

import com.keita.filingcabinet.validator.PagingConstraint;
import com.keita.filingcabinet.validator.folder.FolderExistConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequest implements Serializable {

    @FolderExistConstraint
    private String folderId;

    @PagingConstraint
    private Integer noPage,size;
}
