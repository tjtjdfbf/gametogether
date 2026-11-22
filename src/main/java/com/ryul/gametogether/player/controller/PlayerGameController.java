package com.ryul.gametogether.player.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.player.DTO.AddPlayerGameRequest;
import com.ryul.gametogether.player.DTO.AddPlayerGameResponse;
import com.ryul.gametogether.player.service.PlayerGameService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequiredArgsConstructor 
public class PlayerGameController {

    private final PlayerGameService playerGameService;

    /*
    기능 : 내 게임 추가
    흐름 1. request의 게임 id, jwt의 사용자 id 추출
        2. 추출 정보를 Service에 전달
        3. 완료된 Service로부터 어느 유저가 어느 게임들을 추가했는지에 대한 정보를 반환
    */
    @PostMapping("/players/my/games")
    public ResponseEntity<AddPlayerGameResponse> addPlayerGame(
        @AuthenticationPrincipal Jwt jwt,
        @Valid @RequestBody AddPlayerGameRequest request)
        {
            Long playerId = Long.valueOf(jwt.getSubject());

            AddPlayerGameResponse response = playerGameService.addGames(request, playerId);
    
            return ResponseEntity.ok(response);
    }
    
}
