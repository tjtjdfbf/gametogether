package com.ryul.gametogether.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryul.gametogether.session.entity.SessionMember;

@Repository 
public interface SessionMemberRepository extends JpaRepository<SessionMember, Long>{
	boolean existsBySession_IdAndPlayer_Id(Long sessionId, Long playerId);
}