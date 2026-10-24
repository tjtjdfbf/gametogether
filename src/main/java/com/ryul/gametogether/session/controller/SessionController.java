package com.ryul.gametogether.session.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.session.dto.SessionResponse;
import com.ryul.gametogether.session.dto.SessionSelectionRequest;
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

	// 멤버의 가입
	@PostMapping ("/sessions/{sessionId}/members")
	public ResponseEntity<Void> joinSession (@AuthenticationPrincipal Jwt jwt, @PathVariable Long sessionId) {
		Long playerId = Long.valueOf(jwt.getSubject());

		sessionService.joinSession(playerId, sessionId);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// 멤버의 게임 후보 선택
	@PatchMapping ("/session/{sessionId}/member/selections")
	public ResponseEntity<SessionResponse> patchSession (
		@AuthenticationPrincipal Jwt jwt,
		@PathVariable("sessionId") Long sessionId,
		@RequestBody SessionSelectionRequest request
	) {
		Long playerId = Long.valueOf(jwt.getId());
		
		return ResponseEntity.ok(null);
	}

}
