package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.entity.User;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.enums.OAuth2ExtendedProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class GithubOAuth2Converter implements OAuth2UserConverter<OAuth2User> {

    @Override
    public User convert(OAuth2User oauth2User) {
        return User.builder()
                .withEmail(oauth2User.getAttribute("email"))
                .withPicture(oauth2User.getAttribute("avatar_url"))
                .withFullName(oauth2User.getAttribute("name"))
                .withViaProvider(supportedProvider())
                .withGrantedAuthorities(withDefaultAuthorities())
                .build();
    }

    @Override
    public OAuth2ExtendedProvider supportedProvider() {
        return OAuth2ExtendedProvider.GITHUB;
    }
}
