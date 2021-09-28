package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class apiImpl {

    private RestTemplate restTemplate;
    final String baseUrl = "https://kms-api-aws-demo.datacustodian.cloud.sap";
    final String url = baseUrl + "/kms/v1/technical_users/credential/activate";
    @Autowired
    public apiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PutMapping(value = "/withRestTemplate", produces = {
            "application/json"}, consumes = {"application/json"})
    public UserCredential activateCredentialWithRestTemplate(@RequestBody UserCredentialRequest userCredentialRequest) {
        var secretRequest = SecretRequest.SecretRequestBuilder.aSecretRequest()
                .withApiKey(new String(userCredentialRequest.getApiKey()))
                .withSecret(new String(userCredentialRequest.getSecret()))
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SecretRequest> request = new HttpEntity<>(secretRequest, headers);
        ResponseEntity<UserCredential> response = restTemplate.exchange(url, HttpMethod.PUT, request, UserCredential.class);
        return response.getBody();
    }

    @PutMapping(value = "/withWebClient", produces = {
            "application/json"}, consumes = {"application/json"})
    public Mono<UserCredential> activateCredentialWithWebClient(@RequestBody UserCredentialRequest userCredentialRequest) {
        var secretRequest = SecretRequest.SecretRequestBuilder.aSecretRequest()
                .withApiKey(new String(userCredentialRequest.getApiKey()))
                .withSecret(new String(userCredentialRequest.getSecret()))
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SecretRequest> request = new HttpEntity<>(secretRequest, headers);
        Mono<UserCredential> userCredentialFlux = WebClient.create().put()
                .uri(url).body(Mono.just(userCredentialRequest),UserCredentialRequest.class).retrieve()
                .bodyToMono(UserCredential.class);
        return userCredentialFlux;
    }
}

