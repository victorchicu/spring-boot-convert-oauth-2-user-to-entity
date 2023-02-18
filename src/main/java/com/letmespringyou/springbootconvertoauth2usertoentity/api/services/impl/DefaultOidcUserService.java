package com.letmespringyou.springbootconvertoauth2usertoentity.api.services.impl;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.strategy.OAuth2UserConverterRegistry;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class DefaultOidcUserService extends OidcUserService {
    private final OAuth2UserConverterRegistry oauth2UserConverterRegistry;

    public DefaultOidcUserService(OAuth2UserConverterRegistry oauth2UserConverterRegistry) {
        this.oauth2UserConverterRegistry = oauth2UserConverterRegistry;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        User user = oauth2UserConverterRegistry.convert(userRequest, oidcUser);
        return new DefaultOidcUser(user.getRoles(), oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}
