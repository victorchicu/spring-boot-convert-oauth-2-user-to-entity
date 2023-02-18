package com.letmespringyou.springbootconvertoauth2usertoentity.api.controllers;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.dto.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public UserDto me(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttribute("email");
    }
}
