package com.ryul.gametogether.session.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryul.gametogether.session.entity.SessionMember;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long>{
	boolean existsBySession_IdAndPlayer_Id(Long sessionId, Long playerId);
	Optional<SessionMember> findBySession_IdAndPlayer_Id(Long sessionId, Long playerId);
}