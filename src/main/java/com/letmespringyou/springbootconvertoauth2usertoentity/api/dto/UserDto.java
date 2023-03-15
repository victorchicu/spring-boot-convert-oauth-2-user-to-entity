package com.letmespringyou.springbootconvertoauth2usertoentity.api.dto;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.oauth2.OAuth2ExtendedProvider;

public class UserDto {
    private String id;
    private String email;
    private String picture;
    private String fullName;
    private String phoneNumber;
    private String location;
    private OAuth2ExtendedProvider provider;

    private UserDto(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setPicture(builder.picture);
        setFullName(builder.fullName);
        setPhoneNumber(builder.phoneNumber);
        setLocation(builder.location);
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        private String fullName;
        private String phoneNumber;
        private String location;
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

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
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
