package com.kplusweb.services_games.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    public SecurityConfig(final SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityFilter securityFilter) throws Exception {
        httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register/**").permitAll()
                    .requestMatchers(HttpMethod.PATCH, "/auth/update/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/auth/get-all ").hasRole("ADMIN")

                    .requestMatchers(HttpMethod.GET, "/categories/**").authenticated()
                    .requestMatchers(HttpMethod.GET, "/products/**").authenticated()

                    .requestMatchers(HttpMethod.POST, "/phone/post").authenticated()
                    .requestMatchers(HttpMethod.GET, "/phone/**").authenticated()

                    .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/products/**").hasRole("SELLER")
                    .requestMatchers(HttpMethod.PATCH, "/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/products/**").hasRole("SELLER")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    
        return httpSecurity.build();
    }
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}