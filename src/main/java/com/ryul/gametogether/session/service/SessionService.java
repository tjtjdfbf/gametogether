package com.ryul.gametogether.session.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ryul.gametogether.game.entity.Game;
import com.ryul.gametogether.game.repository.GameRepository;
import com.ryul.gametogether.player.entity.Player;
import com.ryul.gametogether.player.repository.PlayerRepository;
import com.ryul.gametogether.session.dto.SessionResponse;
import com.ryul.gametogether.session.dto.SessionSelectionRequest;
import com.ryul.gametogether.session.entity.Session;
import com.ryul.gametogether.session.entity.SessionMember;
import com.ryul.gametogether.session.entity.SessionMemberGameSelection;
import com.ryul.gametogether.session.entity.SessionMemberTimeSelection;
import com.ryul.gametogether.session.repository.SessionMemberGameSelectionRepository;
import com.ryul.gametogether.session.repository.SessionMemberRepository;
import com.ryul.gametogether.session.repository.SessionMemberTimeSelectionRepository;
import com.ryul.gametogether.session.repository.SessionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SessionService {
	private final SessionRepository sessionRepository;
	private final SessionMemberRepository sessionMemberRepository;
	private final SessionMemberGameSelectionRepository gameSelectionRepository;
	private final SessionMemberTimeSelectionRepository timeSelectionRepository;
	private final PlayerRepository playerRepository;
	private final GameRepository gameRepository;

	public SessionResponse newSession(Long playerId) {
		Player player = playerRepository.findById(playerId)
		.orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다.") );

		Session newSession = new Session(player);

		newSession = sessionRepository.save(newSession);

		// 생성한 세션의 멤버로 호스트를 추가
		SessionMember sessionMember = new SessionMember(newSession, player);

		sessionMemberRepository.save(sessionMember);

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

		if (sessionMemberRepository.existsBySession_IdAndPlayer_Id(sessionId, playerId)) {
			throw new IllegalStateException("이미 참가한 세션입니다.");
		}

		SessionMember member = new SessionMember(session, player);
		
		sessionMemberRepository.save(member);
	}

	@Transactional 
	public void patchSession(Long playerId, Long sessionId, SessionSelectionRequest request) {
		SessionMember member = sessionMemberRepository.findBySession_IdAndPlayer_Id(sessionId, playerId)
			.orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다."));

		// 게임 후보 저장
		if (request.gameIds() != null) {
			List<Long> gameIds = request.gameIds().stream().distinct().toList();

			List<Game> games = gameRepository.findAllById(gameIds);

			if (gameIds.size() != games.size()) {
				throw new IllegalArgumentException("존재하지 않는 게임이 포함되어 있습니다.");
			}

			gameSelectionRepository.deleteByMemberId(member.getId());
			gameSelectionRepository.flush();
	
			List<SessionMemberGameSelection> gameSelections = games.stream()
				.map(game -> new SessionMemberGameSelection(member, game))
				.toList();
	
			gameSelectionRepository.saveAll(gameSelections);
		}
		
		// 시간 후보 저장

		// 비교할 현재 시간을 하나의 변수로 통일
		LocalDateTime now = LocalDateTime.now();

		if (request.availableTimes() != null) {
			for (LocalDateTime time : request.availableTimes()) {
				// 시간 정각 여부 검증
				if (time.getMinute() != 0 ||
				time.getSecond() != 0 ||
				time.getNano() != 0
				) {
					throw new IllegalArgumentException(
						"시간은 정각 단위로만 선택할 수 있습니다."
					);
				}
				// 과거 시간 여부 검증
				if (time.isBefore(now)) {
					throw new IllegalArgumentException(
						"지나간 시간은 선택할 수 없습니다."
					);
				}
			}

			List<SessionMemberTimeSelection> timeSelection = request.availableTimes().stream().distinct()
				.map(time -> new SessionMemberTimeSelection(member, time))
				.toList();
			
			timeSelectionRepository.deleteByMemberId(member.getId());
			timeSelectionRepository.flush();
	
			timeSelectionRepository.saveAll(timeSelection);
		}
	}
}
