package com.letmespringyou.springbootconvertoauth2usertoentity.api.services;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByEmail(String email);
}
