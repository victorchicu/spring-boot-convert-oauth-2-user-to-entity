package com.letmespringyou.springbootconvertoauth2usertoentity.api.strategy;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2UserConverter<T extends OAuth2User> {
    User convert(OAuth2UserRequest request, T oauth2User);

    String supportedRequestType();
}
