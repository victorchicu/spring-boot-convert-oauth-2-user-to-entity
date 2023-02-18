package com.letmespringyou.springbootconvertoauth2usertoentity.api.strategy;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2UserConverterRegistry {
    private final Map<String, OAuth2UserConverter<OAuth2User>> oauth2UserConverters;

    public OAuth2UserConverterRegistry(Map<String, OAuth2UserConverter<OAuth2User>> oauth2UserConverters) {
        this.oauth2UserConverters = oauth2UserConverters;
    }

    public User convert(OAuth2UserRequest request, OAuth2User oauth2User) {
        if (!oauth2UserConverters.containsKey(request.getClientRegistration().getRegistrationId())) {
            throw new IllegalArgumentException("Unknown client registration id: " + request.getClientRegistration().getRegistrationId());
        }
        return oauth2UserConverters.get(request.getClientRegistration().getRegistrationId()).convert(request, oauth2User);
    }
}
