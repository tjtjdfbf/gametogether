package com.ryul.gametogether.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryul.gametogether.game.dto.GameResponse;
import com.ryul.gametogether.game.entity.Game;
import com.ryul.gametogether.game.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class GameService {
	private final GameRepository gameRepository;

	public List<GameResponse> findAllGameList() {
		List<Game> allGameEntity = gameRepository.findAll();
		List<GameResponse> allGameResponses = allGameEntity.stream().map(game -> new GameResponse(game.getName(), game.getGenre())).toList();
		return allGameResponses;
	}
}
