package com.ryul.gametogether.player.DTO;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record UpdateTogetherRequest (
	@NotNull List<@NotNull Long> onGameIds,
	@NotNull List<@NotNull Long> offGameIds
) {}
