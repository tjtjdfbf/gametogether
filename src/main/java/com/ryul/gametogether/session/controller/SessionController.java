package com.ryul.gametogether.session.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.session.dto.SessionResponse;
import com.ryul.gametogether.session.service.SessionService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class SessionController {
	private final SessionService sessionService;

	@PostMapping ("/session")
	public ResponseEntity<SessionResponse> newSession (@AuthenticationPrincipal Jwt jwt) {
		Long playerId = Long.valueOf(jwt.getSubject());

		return ResponseEntity.ok(sessionService.newSession(playerId));
	}
}
