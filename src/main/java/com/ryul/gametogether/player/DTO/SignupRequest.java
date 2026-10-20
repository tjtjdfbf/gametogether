package com.ryul.gametogether.player.DTO;

import jakarta.validation.constraints.NotBlank;
public record SignupRequest (
    @NotBlank 
    String loginId,
    @NotBlank 
    String password,
    String name
) {}
