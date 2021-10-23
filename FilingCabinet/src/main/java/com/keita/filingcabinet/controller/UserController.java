package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.CategoryDetailUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.service.UserService;
import com.keita.filingcabinet.validator.folder.FolderExistConstraint;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER','SUDO')")
public class UserController {

    private final UserService userService;

    @GetMapping("/getListFileDetailUserView/{folderId}")
    public List<FileDetailUserView> getListFileDetailUserView(@Valid @ModelAttribute PagingRequest pagingRequest,
                                                              @PathVariable @Validated String folderId) {
        return userService.getListFileDetailUserView(pagingRequest, folderId);
    }

    @GetMapping("/getListCategoryDetailUserView")
    public List<CategoryDetailUserView> getListCategoryDetailUserView() {
        return userService.getListCategoryDetailUserView();
    }
}
