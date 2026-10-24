package com.ryul.gametogether.session.service;

import org.springframework.stereotype.Service;

import com.ryul.gametogether.player.entity.Player;
import com.ryul.gametogether.player.repository.PlayerRepository;
import com.ryul.gametogether.session.dto.SessionResponse;
import com.ryul.gametogether.session.dto.SessionSelectionRequest;
import com.ryul.gametogether.session.entity.Session;
import com.ryul.gametogether.session.entity.SessionMember;
import com.ryul.gametogether.session.repository.SessionMemberRepository;
import com.ryul.gametogether.session.repository.SessionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SessionService {
	private final SessionRepository sessionRepository;
	private final SessionMemberRepository sessionMemberRepository;
	private final PlayerRepository playerRepository;

	public SessionResponse newSession(Long playerId) {
		Player player = playerRepository.findById(playerId)
		.orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다.") );

		Session newSession = new Session(player);

		sessionRepository.save(newSession);

		SessionResponse newSessionDto = new SessionResponse(
			newSession.getHost().getId(),
			newSession.getStatus(),
			newSession.getSelectedTime(),
			newSession.getSelectedGame()
		);
		
		return newSessionDto;
	}


	public void joinSession(Long playerId, Long sessionId) {
		Session session = sessionRepository.findById(sessionId)
			.orElseThrow(() -> new EntityNotFoundException("세션을 찾을 수 없습니다."));
		
		Player player = playerRepository.findById(playerId)
			.orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

		if (!sessionMemberRepository.existsBySession_IdAndPlayer_Id(sessionId, playerId)) {
			throw new IllegalStateException("이미 참가한 세션입니다.");
		}

		SessionMember member = new SessionMember(session, player);
		
		sessionMemberRepository.save(member);
	}

	public SessionResponse patchSession(Long playerId, Long sessionId, SessionSelectionRequest request) {
		
		
		return null;
	}
}
