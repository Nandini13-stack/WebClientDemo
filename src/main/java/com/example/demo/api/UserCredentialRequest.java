package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredentialRequest {
    @JsonProperty("api_key")
    private String apiKey = null;

    @JsonProperty("secret")
    private String secret = null;


    public String getApiKey() {
        return apiKey;
    }


    public String getSecret() {
        return secret;
    }

    public static final class UserCredentialRequestBuilder {
        private String apiKey = null;

        private String secret = null;

        private UserCredentialRequestBuilder() {
        }

        public static UserCredentialRequestBuilder newInstance() {
            return new UserCredentialRequestBuilder();
        }

        public UserCredentialRequestBuilder withApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public UserCredentialRequestBuilder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public UserCredentialRequest build() {
            UserCredentialRequest userCredentialRequest = new UserCredentialRequest();
            userCredentialRequest.apiKey = this.apiKey;
            userCredentialRequest.secret = this.secret;
            return userCredentialRequest;
        }
    }
}
