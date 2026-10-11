package com.ryul.gametogether.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig {

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers(
            request -> "POST".equals(request.getMethod())
            && "/player".equals(request.getServletPath())
        ))
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/player").permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
