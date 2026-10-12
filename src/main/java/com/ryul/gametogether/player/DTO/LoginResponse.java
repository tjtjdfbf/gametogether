package com.ryul.gametogether.player.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class LoginResponse {
    private String accessToekn;
    private String tokenType;
    private long expiresIn;
    private Long playerId;
    private String name;
}
