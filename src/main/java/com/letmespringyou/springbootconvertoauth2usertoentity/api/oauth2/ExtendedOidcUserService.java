package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.services.UserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class ExtendedOidcUserService extends OidcUserService {
    private final UserService userService;
    private final OAuth2UserConverterRegistry oauth2UserConverterRegistry;

    public ExtendedOidcUserService(UserService userService, OAuth2UserConverterRegistry oauth2UserConverterRegistry) {
        this.userService = userService;
        this.oauth2UserConverterRegistry = oauth2UserConverterRegistry;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(oidcUserRequest);
        User user = oauth2UserConverterRegistry.convert(oidcUserRequest, oidcUser);
        userService.findOneBy(user.getEmail(), user.getSocialProvider()).orElseGet(() -> userService.save(user));
        String nameAttributeKey = oidcUserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        return new SocialOAuth2User(user.getGrantedAuthorities(), oidcUser.getIdToken(), nameAttributeKey, user.getSocialProvider());
    }
}