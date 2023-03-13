package com.letmespringyou.springbootconvertoauth2usertoentity.api.services.impl;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2.OAuth2UserConverterRegistry;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DefaultOAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserService userService;
    private final OAuth2UserConverterRegistry oauth2UserConverterRegistry;

    public DefaultOAuth2UserServiceImpl(UserService userService, OAuth2UserConverterRegistry oauth2UserConverterRegistry) {
        this.userService = userService;
        this.oauth2UserConverterRegistry = oauth2UserConverterRegistry;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oauth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(oauth2UserRequest);
        User user = userService.findByEmail(oauth2User.getAttribute("email"))
                .orElseGet(() ->
                        userService.save(oauth2UserConverterRegistry.convert(oauth2UserRequest, oauth2User))
                );
        String nameAttributeKey = oauth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();
        return new DefaultOAuth2User(user.getGrantedAuthorities(), oauth2User.getAttributes(), nameAttributeKey);
    }
}