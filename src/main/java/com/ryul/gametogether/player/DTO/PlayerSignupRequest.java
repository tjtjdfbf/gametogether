package com.ryul.gametogether.player.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class PlayerSignupRequest {

    @NotBlank 
    private String loginId;

    private String password;

    private String name;
}
