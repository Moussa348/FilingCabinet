package com.keita.filingcabinet.model.dto;

import com.keita.filingcabinet.validator.folder.FolderExistConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileCreation implements Serializable {

    @FolderExistConstraint
    private String folderId;

    @NotBlank(message = "the description can't be blank")
    private String description;

    @NotNull(message = "the multipartFile of upload can't be null")
    private MultipartFile multipartFile;
}
