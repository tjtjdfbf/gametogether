package com.ryul.gametogether.player.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryul.gametogether.player.entity.PlayerGame;

public interface PlayerGameRepository extends JpaRepository<PlayerGame, Long>{
    List<PlayerGame> findByPlayerGame_IdAndGame_IdIn(Long playerId, Collection<Long> gameIds);
}