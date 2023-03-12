package com.letmespringyou.springbootconvertoauth2usertoentity.api.dto;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.enums.OAuth2ExtendedProvider;

public class UserDto {
    private String id;
    private String email;
    private String picture;
    private OAuth2ExtendedProvider provider;

    private UserDto(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setPicture(builder.picture);
        setProvider(builder.provider);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public OAuth2ExtendedProvider getProvider() {
        return provider;
    }

    public void setProvider(OAuth2ExtendedProvider provider) {
        this.provider = provider;
    }

    public static final class Builder {
        private String id;
        private String email;
        private String picture;
        private OAuth2ExtendedProvider provider;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder withProvider(OAuth2ExtendedProvider provider) {
            this.provider = provider;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
