package com.ryul.gametogether.player.controller;

import com.ryul.gametogether.player.service.PlayerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.player.DTO.LoginRequest;
import com.ryul.gametogether.player.DTO.LoginResponse;
import com.ryul.gametogether.player.DTO.SignupRequest;

import java.net.URI;
import java.util.Map;

import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    URI location = null;

    @PostMapping("/players")
    public ResponseEntity<Map<String, String>> addPlayer(@Valid @RequestBody SignupRequest request) {

        Long playerId = playerService.addPlayer(request);

        location = URI.create("/players/"+playerId);

        return ResponseEntity
            .created(location)
            .body(Map.of("message", "회원가입 완료"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        LoginResponse response = playerService.login(request);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal Jwt jwt, @RequestParam String param) {
        return new String(param);
    }
    
    
}
