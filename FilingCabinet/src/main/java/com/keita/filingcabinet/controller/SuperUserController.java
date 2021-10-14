package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.CategoryDetailSuperUserView;
import com.keita.filingcabinet.model.dto.FileDetailSuperUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.service.SuperUserService;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@RequestMapping("/superUser")
@PreAuthorize("hasAuthority('SUDO')")
public class SuperUserController {

    public SuperUserController(SuperUserService superUserService) {
        this.superUserService = superUserService;
    }

    private final SuperUserService superUserService;

    @GetMapping("/getListFileDetailSuperUserView")
    public List<FileDetailSuperUserView> getListFileDetailSuperUserView(@Valid @ModelAttribute PagingRequest pagingRequest) {
        return superUserService.getListFileDetailSuperUserView(pagingRequest);
    }

    @GetMapping("/getListCategoryDetailSuperUserView")
    public List<CategoryDetailSuperUserView> getListCategoryDetailSuperUserView() {
        return superUserService.getListCategoryDetailSuperUserView();
    }
}
