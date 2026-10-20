package com.ryul.gametogether.player.DTO;


public record LoginResponse( 
    String accessToekn,
    String tokenType,
    long expiresIn,
    Long playerId,
    String name
) {}
