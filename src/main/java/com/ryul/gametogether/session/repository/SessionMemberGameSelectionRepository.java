package com.ryul.gametogether.session.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryul.gametogether.session.entity.SessionMemberGameSelection;

public interface SessionMemberGameSelectionRepository
    extends JpaRepository<SessionMemberGameSelection, Long>{
    // Long = 삭제한 데이터 개수
    Long deleteByMemberId(Long sessionMemberId);
    List<SessionMemberGameSelection> findByMemberId(Long sessionMemberId);
}
