package com.ryul.gametogether.player.repository;

import com.ryul.gametogether.player.entity.Player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    boolean existsByLoginId(String loginId);
}