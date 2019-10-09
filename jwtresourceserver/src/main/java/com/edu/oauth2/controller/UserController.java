package com.edu.oauth2.controller;

import com.edu.oauth2.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/api/userInfo")
    public ResponseEntity<UserInfo> getUerInfo() {
        String username = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = username + "@test.com";

        UserInfo userInfo = new UserInfo(username, email);

        return ResponseEntity.ok(userInfo);
    }

}