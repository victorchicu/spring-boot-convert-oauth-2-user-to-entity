package com.letmespringyou.springbootconvertoauth2usertoentity.api.dto;

public class UserDto {
    private String id;
    private String email;
    private String phoneNumber;

    public UserDto() {
        //
    }

    private UserDto(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setPhoneNumber(builder.phoneNumber);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public static final class Builder {
        private String id;
        private String email;
        private String phoneNumber;

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

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
