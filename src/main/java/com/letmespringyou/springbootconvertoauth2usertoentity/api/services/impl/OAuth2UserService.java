package com.letmespringyou.springbootconvertoauth2usertoentity.api.services.impl;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.repository.UserMongoRepository;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OAuth2UserService implements UserService {
    private final UserMongoRepository userMongoRepository;

    public OAuth2UserService(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    public User save(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userMongoRepository.findByEmail(email);
    }
}
