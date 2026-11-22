package com.ryul.gametogether.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryul.gametogether.session.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

} 
