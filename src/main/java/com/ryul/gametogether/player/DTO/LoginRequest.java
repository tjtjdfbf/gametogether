package com.ryul.gametogether.player.DTO;

import jakarta.validation.constraints.NotBlank;


public record LoginRequest (
    @NotBlank
    String loginId,
    @NotBlank
    String password
) {}