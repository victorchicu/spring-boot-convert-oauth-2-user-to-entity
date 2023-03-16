package com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({OAuth2ClientProperties.class})
public class OAuth2HttpSecurityConfigurer {
    private final OidcUserService oidcUserService;
    private final OAuth2ClientProperties oauth2ClientProperties;
    private final DefaultOAuth2UserService defaultOAuth2UserService;

    public OAuth2HttpSecurityConfigurer(
            OidcUserService oidcUserService,
            OAuth2ClientProperties oauth2ClientProperties,
            DefaultOAuth2UserService defaultOAuth2UserService
    ) {
        this.oidcUserService = oidcUserService;
        this.oauth2ClientProperties = oauth2ClientProperties;
        this.defaultOAuth2UserService = defaultOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/assets/**", "/sign-in")
                                .permitAll()
                )
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.anyRequest()
                                .authenticated()
                )
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                        httpSecurityOAuth2LoginConfigurer
                                .loginPage("/sign-in")
                                .successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
                                .userInfoEndpoint((OAuth2LoginConfigurer<HttpSecurity>.UserInfoEndpointConfig userInfoEndpointConfig) ->
                                        userInfoEndpointConfig
                                                .userService(defaultOAuth2UserService)
                                                .oidcUserService(oidcUserService)
                                )
                                .authorizedClientService(new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository()))
                );
        return http.build();
    }

    private ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = oauth2ClientProperties.getRegistration().entrySet().stream()
                .map(OAuth2HttpSecurityConfigurer::toClientRegistration)
                .toList();
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private static ClientRegistration toClientRegistration(Map.Entry<String, OAuth2ClientProperties.Registration> entry) {
        return OAuth2ExtendedProvider.valueOf(entry.getKey()).getBuilder(entry.getKey())
                .clientId(entry.getValue().getClientId())
                .clientSecret(entry.getValue().getClientSecret())
                .build();
    }
}