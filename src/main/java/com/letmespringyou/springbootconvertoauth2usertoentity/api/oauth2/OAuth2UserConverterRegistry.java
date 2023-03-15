package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Optional;

public class OAuth2UserConverterRegistry {
    private final Map<OAuth2ExtendedProvider, OAuth2UserConverter<OAuth2User>> oauth2UserConverters;

    public OAuth2UserConverterRegistry(Map<OAuth2ExtendedProvider, OAuth2UserConverter<OAuth2User>> oauth2UserConverters) {
        this.oauth2UserConverters = oauth2UserConverters;
    }

    public User convert(OAuth2UserRequest oauth2UserRequest, OAuth2User oauth2User) {
        Optional<OAuth2ExtendedProvider> extendedProvider = toExtendedProvider(oauth2UserRequest);
        if (extendedProvider.isEmpty() || !oauth2UserConverters.containsKey(extendedProvider.get())) {
            throw new IllegalArgumentException("Unknown client registration id: " + oauth2UserRequest.getClientRegistration().getRegistrationId());
        }
        return oauth2UserConverters.get(extendedProvider.get()).convert(oauth2User);
    }

    private static Optional<OAuth2ExtendedProvider> toExtendedProvider(OAuth2UserRequest request) {
        OAuth2ExtendedProvider extendedProvider = null;
        try {
            extendedProvider = OAuth2ExtendedProvider.valueOf(request.getClientRegistration().getRegistrationId());
        } catch (Exception e) {
            //ignore exception
        }
        return Optional.ofNullable(extendedProvider);
    }
}