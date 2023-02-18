package com.letmespringyou.springbootconvertoauth2usertoentity.api.strategy;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class GithubOAuth2Converter implements OAuth2UserConverter<OAuth2User> {

    @Override
    public User convert(OAuth2UserRequest request, OAuth2User oauth2User) {
        return User.builder()
                .build();
    }

    @Override
    public String supportedRequestType() {
        return "GITHUB";
    }
}
