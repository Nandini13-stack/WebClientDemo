package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    @JsonProperty("access_token")
    private String accessToken = null;

    @JsonProperty("expires_in")
    private Integer expiresIn = null;

    @JsonProperty("refresh_token")
    private String refreshToken = null;

    @JsonProperty("token_type")
    private String tokenType = null;

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public static final class AuthResponseBuilder {
        private String accessToken = null;

        private Integer expiresIn = null;

        private String refreshToken = null;

        private String tokenType = null;

        private AuthResponseBuilder() {
        }

        public static AuthResponseBuilder anAuthResponse() {
            return new AuthResponseBuilder();
        }

        public AuthResponseBuilder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AuthResponseBuilder withExpiresIn(Integer expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public AuthResponseBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthResponseBuilder withTokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public AuthResponse build() {
            AuthResponse authResponse = new AuthResponse();
            authResponse.accessToken = this.accessToken;
            authResponse.tokenType = this.tokenType;
            authResponse.expiresIn = this.expiresIn;
            authResponse.refreshToken = this.refreshToken;
            return authResponse;
        }
    }
}