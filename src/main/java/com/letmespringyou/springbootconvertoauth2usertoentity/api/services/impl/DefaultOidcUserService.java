package com.letmespringyou.springbootconvertoauth2usertoentity.api.services.impl;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2.OAuth2UserConverterRegistry;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DefaultOidcUserService extends OidcUserService {
    private final UserService userService;
    private final OAuth2UserConverterRegistry oauth2UserConverterRegistry;

    public DefaultOidcUserService(UserService userService, OAuth2UserConverterRegistry oauth2UserConverterRegistry) {
        this.userService = userService;
        this.oauth2UserConverterRegistry = oauth2UserConverterRegistry;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOidcUser oidcUser = (DefaultOidcUser) super.loadUser(userRequest);
        User user = oauth2UserConverterRegistry.convert(userRequest, oidcUser);
        Set<GrantedAuthority> grantedAuthorities = userService.findByEmail(user.getEmail())
                .orElseGet(() -> userService.save(user))
                .getGrantedAuthorities();
        return new DefaultOidcUser(grantedAuthorities, oidcUser.getIdToken(), oidcUser.getName());
    }
}