package com.edu.oauth2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class AuthCodeRestTemplateApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthCodeRestTemplateApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        context.getSessionCookieConfig().setName("client-session");
    }

}
