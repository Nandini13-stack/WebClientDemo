package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TechnicalUserAuthRequest {
    @JsonProperty("api_key")
    private String apiKey = null;

    @JsonProperty("password")
    private String password = null;

    public String getApiKey() {
        return apiKey;
    }

    public String getPassword() {
        return password;
    }

    public static final class TechnicalUserAuthRequestBuilder {
        private String apiKey = null;

        private String password = null;

        private TechnicalUserAuthRequestBuilder() {
        }

        public static TechnicalUserAuthRequestBuilder aTechnicalUserAuthRequest() {
            return new TechnicalUserAuthRequestBuilder();
        }

        public TechnicalUserAuthRequestBuilder withApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public TechnicalUserAuthRequestBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public TechnicalUserAuthRequest build() {
            TechnicalUserAuthRequest technicalUserAuthRequest = new TechnicalUserAuthRequest();
            technicalUserAuthRequest.password = this.password;
            technicalUserAuthRequest.apiKey = this.apiKey;
            return technicalUserAuthRequest;
        }
    }
}