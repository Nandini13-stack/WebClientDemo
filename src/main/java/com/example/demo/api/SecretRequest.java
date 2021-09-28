package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecretRequest {
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

    public static final class SecretRequestBuilder {
        private String apiKey = null;

        private String secret = null;

        private SecretRequestBuilder() {
        }

        public static SecretRequestBuilder aSecretRequest() {
            return new SecretRequestBuilder();
        }

        public SecretRequestBuilder withApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public SecretRequestBuilder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public SecretRequest build() {
            SecretRequest secretRequest = new SecretRequest();
            secretRequest.secret = this.secret;
            secretRequest.apiKey = this.apiKey;
            return secretRequest;
        }
    }
}
