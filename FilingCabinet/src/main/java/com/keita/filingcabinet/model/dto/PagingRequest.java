package com.keita.filingcabinet.model.dto;

import com.keita.filingcabinet.validator.PagingNumberConstraint;
import com.keita.filingcabinet.validator.PagingSizeConstraint;
import com.keita.filingcabinet.validator.folder.FolderExistConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequest implements Serializable {

    @PagingNumberConstraint
    private Integer noPage;

    @PagingSizeConstraint
    private Integer size;

}