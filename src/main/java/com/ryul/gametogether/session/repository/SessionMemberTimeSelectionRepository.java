package com.ryul.gametogether.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryul.gametogether.session.entity.SessionMemberTimeSelection;

public interface SessionMemberTimeSelectionRepository
    extends JpaRepository<SessionMemberTimeSelection, Long>{   
    Long deleteByMemberId(Long sessionMemberId);
}
