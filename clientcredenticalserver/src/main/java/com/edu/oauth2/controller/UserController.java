package com.edu.oauth2.controller;

import com.edu.oauth2.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/api/userInfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("xiaoyao");
        userInfo.setEmail("xiaoyao@test.com");
        return ResponseEntity.ok(userInfo);
    }
}
