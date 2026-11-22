package com.ryul.gametogether.player.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter 
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Player {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull 
    @Column(nullable = false, unique = true, length = 30)
    private String loginId;

    @NotBlank 
    @NotNull
    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 30)
    private String name;

    public Player(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    private String changePassword(String encodedPassword) {
        this.password = encodedPassword;
        return "암호 변경 완료";
    }

    private String ChangeName(String requestName) {
        this.name = requestName;
        return "닉네임 변경 완료";
    }
}
