package com.ryul.gametogether.player.DTO;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record AddPlayerGameRequest(
    @NotEmpty
    List<Long> gameIds
) {}
