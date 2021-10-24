package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.CategoryDetailSuperUserView;
import com.keita.filingcabinet.model.dto.FileDetailSuperUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.service.LogService;
import com.keita.filingcabinet.service.SuperUserService;
import com.keita.filingcabinet.validator.folder.FolderExistConstraint;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/superUser")
@PreAuthorize("hasAuthority('SUDO')")
public class SuperUserController {

    private final SuperUserService superUserService;

    private final LogService logService;

    @GetMapping("/getListFileDetailSuperUserView/{folderId}")
    public List<FileDetailSuperUserView> getListFileDetailSuperUserView(@Valid @ModelAttribute PagingRequest pagingRequest,
                                                                        @PathVariable @Valid @FolderExistConstraint String folderId) {
        return superUserService.getListFileDetailSuperUserView(pagingRequest, folderId);
    }

    @GetMapping("/getListCategoryDetailSuperUserView")
    public List<CategoryDetailSuperUserView> getListCategoryDetailSuperUserView() {
        return superUserService.getListCategoryDetailSuperUserView();
    }

    @GetMapping("/getListLogByFileId/{fileId}")
    public List<Log> getListLogByFileId(@PathVariable String fileId,
                                     @Valid @ModelAttribute PagingRequest pagingRequest) {
        return logService.getListLogByFileId(fileId, pagingRequest);
    }

}
