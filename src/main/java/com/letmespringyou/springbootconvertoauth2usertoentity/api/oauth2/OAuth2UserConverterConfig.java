package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Configuration
public class OAuth2UserConverterConfig {
    @Bean
    public OAuth2UserConverterRegistry oauth2UserConverterRegistry(List<OAuth2UserConverter<OAuth2User>> oauth2UserConverters) {
        return new OAuth2UserConverterRegistry(
                oauth2UserConverters.stream()
                        .collect(
                                toMap(OAuth2UserConverter::supportedProvider, Function.identity())
                        )
        );
    }
}
