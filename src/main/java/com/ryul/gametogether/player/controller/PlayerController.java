package com.ryul.gametogether.player.controller;

import com.ryul.gametogether.player.service.PlayerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.player.DTO.PlayerSignupRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
public class PlayerController {
    private final PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/player")
    @Valid 
    public String addPlayer(@RequestBody PlayerSignupRequest request) {

        String result = playerService.addPlayer(request);

        return result;
    }
}
