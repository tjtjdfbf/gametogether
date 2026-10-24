package com.ryul.gametogether.session.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record SessionSelectionRequest(
	List<@NotNull Long> gameIds,
	List<@NotNull LocalDateTime> availableTimes
) {
} 
