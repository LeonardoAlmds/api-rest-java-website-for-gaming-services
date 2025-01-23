package com.kplusweb.services_games.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagSeguroConfig {

    @Value("${pagseguro.email}")
    private String email;

    @Value("${pagseguro.token}")
    private String token;

    @Value("${pagseguro.base-url}")
    private String baseUrl;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
