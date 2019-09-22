package com.edu.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    private static final String CLIENT_ID = "clientapp";
    // encoding method prefix is required for DelegatingPasswordEncoder which is default since Spring Security 5.0.0.RC1
    // you can use one of bcrypt/noop/pbkdf2/scrypt/sha256
    // you can change default behaviour by providing a bean with the encoder you want
    // more: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
    static final String CLIENT_SECRET = "123456";

    private static final String IMPILICT = "implicit";
    private static final String SCOPE_READ_USER_INFO = "READ_USER_INFO";
    private static final String SCOPE_READ_CONTACT = "READ_CONTACT";

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .redirectUris("http://localhost:9001/callback")
                .authorizedGrantTypes(IMPILICT)
                .accessTokenValiditySeconds(120)
                .scopes(SCOPE_READ_USER_INFO,SCOPE_READ_CONTACT);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
