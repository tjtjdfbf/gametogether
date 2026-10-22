package com.ryul.gametogether.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryul.gametogether.session.entity.Session;

@Repository 
public interface SessionRepository extends JpaRepository<Session, Long> {

} 
