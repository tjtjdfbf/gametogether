package com.ryul.gametogether.session.entity;

import com.ryul.gametogether.game.entity.Game;

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

@Entity 
@Table (
	uniqueConstraints =
		@UniqueConstraint (
			name = "UQ_session_member_game_selection_session_member_id_game_id",
			columnNames = {"session_member_id", "game_id"}
		)
)
public class SessionMemberGameSelection {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "session_member_id", nullable = false)
	private SessionMember member;

	@NotNull 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "game_id", nullable = false)
	private Game game;
}
