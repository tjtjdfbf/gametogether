package com.ryul.gametogether.session.dto;

import java.time.LocalDateTime;

import com.ryul.gametogether.game.entity.Game;
import com.ryul.gametogether.session.SessionStatus;

public record SessionResponse(
	Long host,
	SessionStatus status,
	LocalDateTime selectedTime,
	Game selectedGame
) {
}
