package com.ryul.gametogether.session.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity 
@Table (
	uniqueConstraints = 
		@UniqueConstraint (
			name = "UQ_session_member_time_selection_session_member_id_avilable_time",
			columnNames = {"session_member_id", "available_time"}
		)
)
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class SessionMemberTimeSelection {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "session_member_id", nullable = false)
	private SessionMember member;

	@NotNull 
	private LocalDateTime availableTime;

	public SessionMemberTimeSelection(SessionMember member, LocalDateTime time) {
		this.member = member;
		this.availableTime = time;
	}
}
