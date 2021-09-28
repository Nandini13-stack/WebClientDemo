package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredential {
    @JsonProperty("active")
    private Boolean active = null;

    @JsonProperty("api_key")
    private String apiKey = null;

    @JsonProperty("enabled")
    private Boolean enabled = null;

    @JsonProperty("expires")
    private String expires = null;

    @JsonProperty("generation")
    private Integer generation = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("secret")
    private String secret = null;

    public Boolean getActive() {
        return active;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getExpires() {
        return expires;
    }

    public Integer getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public String getSecret() {
        return secret;
    }

    public static final class UserCredentialBuilder {
        private Boolean active = null;

        private String apiKey = null;

        private Boolean enabled = null;

        private String expires = null;

        private Integer generation = null;

        private String name = null;

        private String secret = null;

        private UserCredentialBuilder() {
        }

        public static UserCredentialBuilder anUserCredential() {
            return new UserCredentialBuilder();
        }

        public UserCredentialBuilder withActive(Boolean active) {
            this.active = active;
            return this;
        }

        public UserCredentialBuilder withApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public UserCredentialBuilder withEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserCredentialBuilder withExpires(String expires) {
            this.expires = expires;
            return this;
        }

        public UserCredentialBuilder withGeneration(Integer generation) {
            this.generation = generation;
            return this;
        }

        public UserCredentialBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserCredentialBuilder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public UserCredential build() {
            UserCredential userCredential = new UserCredential();
            userCredential.generation = this.generation;
            userCredential.secret = this.secret;
            userCredential.active = this.active;
            userCredential.name = this.name;
            userCredential.enabled = this.enabled;
            userCredential.apiKey = this.apiKey;
            userCredential.expires = this.expires;
            return userCredential;
        }
    }
}
