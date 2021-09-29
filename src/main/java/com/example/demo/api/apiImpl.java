package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class apiImpl {

    private RestTemplate restTemplate;
    final String baseUrl = "https://kms-api-aws-demo.datacustodian.cloud.sap";
    final String url = baseUrl + "/kms/v1/technical_users/credential/activate";
    final String authenticateUrl = baseUrl + "/kms/v1/auth/request";
    public static final Logger log = LoggerFactory.getLogger(apiImpl.class);
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
        log.info("Rest Template response here" + response.getBody().getSecret());
        log.info("Exiting blocking response");
        return response.getBody();
    }

    @PutMapping(value = "/withWebClient", produces = {
            "application/json"}, consumes = {"application/json"})
    public UserCredential activateCredentialWithWebClient(@RequestBody UserCredentialRequest userCredentialRequest) {
        var secretRequest = SecretRequest.SecretRequestBuilder.aSecretRequest()
                .withApiKey(new String(userCredentialRequest.getApiKey()))
                .withSecret(new String(userCredentialRequest.getSecret()))
                .build();
        /*
        Mono<UserCredential> userCredentialMono = WebClient.create().put()
                .uri(url).body(Mono.just(userCredentialRequest),UserCredentialRequest.class).retrieve()
                .bodyToMono(UserCredential.class);
       // The subscribe call does not work, leads to a dump after the response is received by the client.
       // Dump exception says, that it received a 401 Unauthorized error from DCKMS.
       // This appears to be very specific to the Activate call.  Subscription in the other call for authentication works fine
       userCredentialMono.subscribe(UserCredential::getApiKey);
       userCredentialMono.subscribe(apiImpl :: handleResponse);
       */

        return WebClient.create().put()
                 .uri(url).body(Mono.just(userCredentialRequest),UserCredentialRequest.class).retrieve()
                   .bodyToMono(UserCredential.class).block();
    }

    private static void handleResponse(UserCredential userCredential){
        log.info("Non blocking response here" + userCredential.getSecret());
    }

    @PostMapping(value = "/AuthenticateWithWebClient", produces = {
            "application/json"}, consumes = {"application/json"})
    public Mono<AuthResponse> authenticate(@RequestBody TechnicalUserAuthRequest technicalUserAuthRequest){
        Mono<AuthResponse> authResponseMono = WebClient.create().post().uri(authenticateUrl).body(Mono.just(technicalUserAuthRequest),TechnicalUserAuthRequest.class)
                .retrieve().bodyToMono(AuthResponse.class);
        // This code can be used to demo that subscribe operation is non blocking . console log displays line 81, then line 80
        authResponseMono.subscribe(apiImpl::handleResponseAuthe);
        log.info("Exiting block");
        return authResponseMono;
    }

    private static void handleResponseAuthe(AuthResponse authResponse){
        log.info("Non blocking response here" + authResponse.getAccessToken());
    }

}

