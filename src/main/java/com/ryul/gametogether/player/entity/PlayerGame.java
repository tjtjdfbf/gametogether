package com.ryul.gametogether.player.entity;

import com.ryul.gametogether.game.entity.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    uniqueConstraints = 
        @UniqueConstraint(
            name = "UQ_player_game_player_id_game_id",
            columnNames = {"player_id", "game_id"}
        )
)
@Getter 
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class PlayerGame {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "player_id", nullable = false)
    private Player player;

    @NotNull 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "game_id", nullable = false)
    private Game game;

    public PlayerGame(Player player, Game game) {
        this.player = player;
        this.game = game;
    }
}
