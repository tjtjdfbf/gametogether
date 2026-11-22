package com.ryul.gametogether.session.entity;

import com.ryul.gametogether.player.entity.Player;

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
			name = "UQ_session_member_session_id_player_id",
			columnNames = {"session_id","player_id"}
		)
)
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class SessionMember {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "session_id", nullable = false)
	private Session session;

	@NotNull 
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "player_id", nullable = false)
	private Player player;

	public SessionMember(Session session, Player player) {
		this.session = session;
		this.player = player;
	}
}
