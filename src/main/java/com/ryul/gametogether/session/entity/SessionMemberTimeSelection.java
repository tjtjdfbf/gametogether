package com.ryul.gametogether.session.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity 
public class SessionMemberTimeSelection {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "session_member_id", nullable = false)
	private SessionMember member;

	private LocalDateTime availableTime;
}
