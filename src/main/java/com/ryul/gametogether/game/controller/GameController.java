package com.ryul.gametogether.game.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryul.gametogether.game.entity.Game;

@RestController 
public class GameController {
	@PostMapping ("/games")
	public ResponseEntity<List<Game>> getAllGame() {
		return ResponseEntity.ok(null);
	}
}
