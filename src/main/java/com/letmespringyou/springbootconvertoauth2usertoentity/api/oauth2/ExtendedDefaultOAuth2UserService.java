package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.services.UserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ExtendedDefaultOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;
    private final OAuth2UserConverterRegistry oauth2UserConverterRegistry;

    public ExtendedDefaultOAuth2UserService(UserService userService, OAuth2UserConverterRegistry oauth2UserConverterRegistry) {
        this.userService = userService;
        this.oauth2UserConverterRegistry = oauth2UserConverterRegistry;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oauth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(oauth2UserRequest);
        User user = oauth2UserConverterRegistry.convert(oauth2UserRequest, oauth2User);
        userService.findUser(user.getEmail(), user.getSocialProvider()).orElseGet(() -> userService.saveUser(user));
        String nameAttributeKey = oauth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OidcIdToken fakeOidcToken = new OidcIdToken(UUID.randomUUID().toString(), Instant.now(), Instant.MAX, oauth2User.getAttributes());
        return new SocialOAuth2User(user.getGrantedAuthorities(), fakeOidcToken, nameAttributeKey, user.getSocialProvider());
    }
}