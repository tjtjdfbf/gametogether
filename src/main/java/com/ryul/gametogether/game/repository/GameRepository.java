package com.ryul.gametogether.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryul.gametogether.game.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    
}
