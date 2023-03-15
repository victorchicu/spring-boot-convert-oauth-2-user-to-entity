package com.letmespringyou.springbootconvertoauth2usertoentity.api.dto;

public class ErrorDto {
    private final String message;

    private ErrorDto(Builder builder) {
        message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String message;

        private Builder() {
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(this);
        }
    }
}
