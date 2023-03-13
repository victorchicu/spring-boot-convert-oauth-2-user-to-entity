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

    @GetMapping("/self")
    public UserDto self(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        return userService.findByEmail(email)
                .map(this::toUserDto)
                .orElseThrow(() -> new OopsSomethingWentWrong("Unable to find a user with email: " + email));
    }

    private UserDto toUserDto(User user) {
        return UserDto.builder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withPicture(user.getPicture())
                .withProvider(user.getViaProvider())
                .build();
    }
}