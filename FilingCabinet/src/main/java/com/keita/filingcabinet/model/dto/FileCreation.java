package com.keita.filingcabinet.model.dto;

import com.keita.filingcabinet.validator.FolderExistConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileCreation implements Serializable {

    @NotNull(message = "the folder id can't be null") @NotEmpty(message = "the folder id can't be empty") @NotBlank(message = "the folder id can't be blank") @FolderExistConstraint
    private String folderId;

    @Valid
    @NotNull(message = "the description can't be null") @NotEmpty(message = "the description can't be empty") @NotBlank(message = "the description can't be blank")
    private String description;

    @Valid
    @NotNull(message = "the name of upload can't be null") @NotEmpty(message = "the name of upload can't be empty") @NotBlank(message = "the name of upload can't be blank")
    private String uploadBy;

    @Valid
    @NotNull(message = "the multipartFile of upload can't be null")
    private MultipartFile multipartFile;
}
