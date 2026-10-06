package com.ryul.gametogether.player.entity;

import com.ryul.gametogether.game.entity.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    uniqueConstraints = 
        @UniqueConstraint(
            name = "UQ_player_game_player_id_game_id",
            columnNames = {"player_id", "game_id"}
        )
)
public class PlayerGame {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull 
    @ManyToOne
    @JoinColumn (name = "player_id", nullable = false)
    private Player player;

    @NotNull 
    @ManyToOne 
    @JoinColumn (name = "game_id", nullable = false)
    private Game game;
}
