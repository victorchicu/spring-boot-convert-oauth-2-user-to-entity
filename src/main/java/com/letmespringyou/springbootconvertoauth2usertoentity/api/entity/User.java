package com.letmespringyou.springbootconvertoauth2usertoentity.api.entity;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.enums.OAuth2ExtendedProvider;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String fullName;
    private String picture;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    private Set<GrantedAuthority> grantedAuthorities;
    private OAuth2ExtendedProvider viaProvider;

    public User() {
        //
    }

    private User(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setFullName(builder.fullName);
        setPicture(builder.picture);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
        setGrantedAuthorities(builder.grantedAuthorities);
        setViaProvider(builder.viaProvider);
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public OAuth2ExtendedProvider getViaProvider() {
        return viaProvider;
    }

    public void setViaProvider(OAuth2ExtendedProvider viaProvider) {
        this.viaProvider = viaProvider;
    }

    public static final class Builder {
        private String id;
        private String email;
        private String fullName;
        private String picture;
        private Instant createdAt;
        private Instant updatedAt;
        private Set<GrantedAuthority> grantedAuthorities;
        private OAuth2ExtendedProvider viaProvider;

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

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder withPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder withCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder withGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
            this.grantedAuthorities = grantedAuthorities;
            return this;
        }

        public Builder withViaProvider(OAuth2ExtendedProvider viaProvider) {
            this.viaProvider = viaProvider;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}