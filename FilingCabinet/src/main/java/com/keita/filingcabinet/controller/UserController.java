package com.keita.filingcabinet.controller;

import com.keita.filingcabinet.model.dto.CategoryDetailUserView;
import com.keita.filingcabinet.model.dto.FileDetailUserView;
import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.service.UserService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getListFileDetailUserView")
    public List<FileDetailUserView> getListFileDetailUserView(@Valid @ModelAttribute PagingRequest pagingRequest) {
        return userService.getListFileDetailUserView(pagingRequest);
    }

    @GetMapping("/getListCategoryDetailUserView")
    public List<CategoryDetailUserView> getListCategoryDetailUserView() {
        return userService.getListCategoryDetailUserView();
    }
}
