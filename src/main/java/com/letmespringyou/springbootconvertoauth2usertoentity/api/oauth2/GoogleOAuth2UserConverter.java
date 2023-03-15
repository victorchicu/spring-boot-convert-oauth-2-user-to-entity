package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuth2UserConverter implements OAuth2UserConverter<OAuth2User> {
    @Override
    public User convert(OAuth2User oauth2User) {
        OidcUser oidcUser = (OidcUser) oauth2User;
        return User.builder()
                .withEmail(oidcUser.getEmail())
                .withFullName(oidcUser.getFullName())
                .withPicture(oidcUser.getPicture())
                .withSocialProvider(supportedProvider())
                .withGrantedAuthorities(withDefaultAuthorities())
                .build();
    }

    @Override
    public OAuth2ExtendedProvider supportedProvider() {
        return OAuth2ExtendedProvider.GOOGLE;
    }
}
