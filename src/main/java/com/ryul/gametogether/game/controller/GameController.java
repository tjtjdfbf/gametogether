package com.ryul.gametogether.game.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.game.dto.GameResponse;
import com.ryul.gametogether.game.entity.Game;
import com.ryul.gametogether.game.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class GameController {
	private final GameService gameService;

	@PostMapping ("/games")
	public ResponseEntity<List<GameResponse>> findAllGame() {
		
		List<GameResponse> allGameList = gameService.findAllGameList();

		return ResponseEntity.ok(allGameList);
	}
}
