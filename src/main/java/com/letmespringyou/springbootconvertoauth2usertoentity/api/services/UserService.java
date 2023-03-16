package com.letmespringyou.springbootconvertoauth2usertoentity.api.services;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2.OAuth2ExtendedProvider;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findUser(String email, OAuth2ExtendedProvider provider);
}
