package com.ryul.gametogether.player.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ryul.gametogether.game.entity.Game;
import com.ryul.gametogether.game.repository.GameRepository;
import com.ryul.gametogether.player.DTO.AddPlayerGameRequest;
import com.ryul.gametogether.player.DTO.AddPlayerGameResponse;
import com.ryul.gametogether.player.entity.Player;
import com.ryul.gametogether.player.entity.PlayerGame;
import com.ryul.gametogether.player.repository.PlayerGameRepository;
import com.ryul.gametogether.player.repository.PlayerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PlayerGameService {
    private PlayerGameRepository playerGameRepository;
    private PlayerRepository playerRepository;
    private GameRepository gameRepository;

    public AddPlayerGameResponse addGames(AddPlayerGameRequest request, Long playerId) {

        // 요청 받은 게임 id 추출
        List<Long> gameIds = request.gameIds().stream().distinct().toList();

        // 요청에 기반한 게임 목록 추출
        // 존재하지 않는 id는 자동 제외
        List<Game> requestgames = gameRepository.findAllById(gameIds);

        // 중복 게임 리스트 = existPlayerGames
        List<PlayerGame> existPlayerGames = playerGameRepository.findByPlayerGame_IdAndGame_IdIn(playerId, gameIds);

        // PlayerGame 저장용 유저 엔티티 생성
        Player player = playerRepository.findById(playerId)
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        List<PlayerGame> toSave = requestgames.stream()
            .filter(game -> !existPlayerGames.contains(game.getId()))
            .map(game -> new PlayerGame(player, game))
            .toList();

        playerGameRepository.saveAll(toSave);

        // GameInfo 리스트 제작
        List<AddPlayerGameResponse.GameInfo> gameInfos = toSave.stream()
            .map(playerGames -> new AddPlayerGameResponse.GameInfo(
                playerGames.getGame().getId(),
                playerGames.getGame().getName()
            )).toList();

        return new AddPlayerGameResponse(
            "내 게임 등록 성공",
            gameInfos.size(),
            gameInfos
        );
    }
}
