package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class GithubOAuth2UserConverter implements OAuth2UserConverter<OAuth2User> {
    @Override
    public User convert(OAuth2User oauth2User) {
        return User.builder()
                .withEmail(oauth2User.getAttribute("email"))
                .withFullName(oauth2User.getAttribute("name"))
                .withPicture(oauth2User.getAttribute("avatar_url"))
                .withLocation(oauth2User.getAttribute("location"))
                .withSocialProvider(supportedProvider())
                .withGrantedAuthorities(withDefaultAuthorities())
                .build();
    }

    @Override
    public OAuth2ExtendedProvider supportedProvider() {
        return OAuth2ExtendedProvider.GITHUB;
    }
}
