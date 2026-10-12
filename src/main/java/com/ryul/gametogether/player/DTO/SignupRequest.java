package com.ryul.gametogether.player.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class SignupRequest {

    @NotBlank 
    private String loginId;

    @NotBlank 
    private String password;

    private String name;
}
