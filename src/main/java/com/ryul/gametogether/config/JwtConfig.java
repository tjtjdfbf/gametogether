package com.ryul.gametogether.config;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration 
public class JwtConfig {
    @Bean
    public SecretKey jwtSecretKey(@Value("${security.jwt.secret}") String secret) {
            byte[] keyBytes = Base64.getDecoder().decode(secret);

            if(keyBytes.length < 32) {
                throw new IllegalArgumentException("JWT 비밀 키는 최소 32바이트여야 합니다.");
            }
            return new SecretKeySpec(keyBytes, "HmacsSHA256");
        }
    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwSecretKey) {
        return NimbusJwtEncoder
            .withSecretKey(jwSecretKey)
            .algorithm(MacAlgorithm.HS256)
            .build();
    }

    @Bean 
    public JwtDecoder jwtDecoder(SecretKey jwSecretKey, @Value ("${security.jwt.issuer}") String issuer) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder
            .withSecretKey(jwSecretKey)
            .macAlgorithm(MacAlgorithm.HS256)
            .build();

        decoder.setJwtValidator(
            JwtValidators.createDefaultWithIssuer(issuer)
        );
        return decoder;
    }
}
