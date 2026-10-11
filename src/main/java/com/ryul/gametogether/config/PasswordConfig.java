package com.ryul.gametogether.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;

@Configuration 
public class PasswordConfig {
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new Argon2Password4jPasswordEncoder();
    }
}
