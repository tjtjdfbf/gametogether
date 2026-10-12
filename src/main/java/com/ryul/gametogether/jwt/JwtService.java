package com.ryul.gametogether.jwt;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ryul.gametogether.player.entity.Player;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class JwtService {
    private final JwtEncoder jwtEncoder;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.access-token-ttl-minutes:15}")
    private long accessTokenTtlMinutes;

    public String createAccessToken(Player player) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(Duration.ofMinutes(accessTokenTtlMinutes));

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer(issuer)
            .subject(player.getId().toString())
            .issuedAt(now)
            .expiresAt(expiresAt)
            .build();

        JwsHeader header = JwsHeader
            .with(MacAlgorithm.HS256)
            .build();

        return jwtEncoder
            .encode(JwtEncoderParameters.from(header, claims))
            .getTokenValue();
    }

    public long getAccessTokenValiditySeconds() {
        return Duration.ofMinutes(accessTokenTtlMinutes).toSeconds();
    }
}
