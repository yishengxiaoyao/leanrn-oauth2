package com.edu.oauth2.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AuthorizationCodeTokenService {
    @Autowired
    private AuthorizationCodeConfiguration configuration;

    public OAuth2Token getToken() {
        RestTemplate rest = new RestTemplate();
        String authBase64 = configuration.encodeCredentials("clientapp",
                "123456");

        RequestEntity<MultiValueMap<String, String>> requestEntity = new RequestEntity<>(
            configuration.getBody(),
            configuration.getHeader(authBase64), HttpMethod.POST,
            URI.create("http://localhost:8080/oauth/token"));

        ResponseEntity<OAuth2Token> responseEntity = rest.exchange(
                requestEntity, OAuth2Token.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        throw new RuntimeException("error trying to retrieve access token");
    }
}
