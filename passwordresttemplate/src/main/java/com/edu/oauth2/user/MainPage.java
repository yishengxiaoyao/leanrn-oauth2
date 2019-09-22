package com.edu.oauth2.user;

import com.edu.oauth2.oauth.AuthorizationCodeTokenService;
import com.edu.oauth2.oauth.OAuth2Token;
import com.edu.oauth2.security.ClientUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


@Controller
public class MainPage {
    @Autowired
    private AuthorizationCodeTokenService tokenService;

    @Autowired
    private UserRepository users;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/mainpage")
    public ModelAndView mainpage() {
        ClientUserDetails userDetails = (ClientUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = userDetails.getClientUser();
        OAuth2Token token = null;
        if (clientUser.getAccessToken() == null) {
            //send post request
            token = tokenService.getToken(clientUser.getUsername(),clientUser.getPassword());
            clientUser.setAccessToken(token.getAccessToken());
        }

        clientUser.setEntries(Arrays.asList(
                new Entry("entry 1"),
                new Entry("entry 2")));

        ModelAndView mv = new ModelAndView("mainpage");
        mv.addObject("user", clientUser);

        tryToGetUserInfo(mv, clientUser.getAccessToken());

        return mv;
    }

    private void tryToGetUserInfo(ModelAndView mv, String token) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);
        String endpoint = "http://localhost:8080/api/userInfo";

        try {
            RequestEntity<Object> request = new RequestEntity<>(
                headers, HttpMethod.GET, URI.create(endpoint));

            ResponseEntity<UserInfo> userInfo = restTemplate.exchange(request, UserInfo.class);

            if (userInfo.getStatusCode().is2xxSuccessful()) {
                mv.addObject("userInfo", userInfo.getBody());
            } else {
                throw new RuntimeException("it was not possible to retrieve user profile");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("it was not possible to retrieve user profile");
        }
    }

}
