package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

public enum OAuth2ExtendedProvider {
    GOOGLE {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(registrationId);
        }
    },
    GITHUB {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            return CommonOAuth2Provider.GITHUB.getBuilder(registrationId);
        }
    },
    FACEBOOK {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(registrationId);
        }
    };

    public abstract ClientRegistration.Builder getBuilder(String registrationId);
}