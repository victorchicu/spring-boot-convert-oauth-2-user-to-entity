package com.letmespringyou.springbootconvertoauth2usertoentity.api.controllers;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.dto.UserDto;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.exceptions.OopsSomethingWentWrong;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto me(@AuthenticationPrincipal OAuth2User principal) {
        return userService.findByEmail(principal.getAttribute("email"))
                .map(this::toUserDto)
                .orElseThrow(() -> new OopsSomethingWentWrong("Couldn't find a user with the email: " + principal.getAttribute("email")));
    }

    private UserDto toUserDto(User user) {
        return UserDto.builder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withPicture(user.getPicture())
                .withProvider(user.getProvider())
                .build();
    }
}