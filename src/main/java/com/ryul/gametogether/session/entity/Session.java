package com.ryul.gametogether.session.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ryul.gametogether.game.entity.Game;
import com.ryul.gametogether.player.entity.Player;
import com.ryul.gametogether.session.SessionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Session {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne 
    @JoinColumn (name = "host_id", nullable = false)
    private Player host;

    @NotNull
    @Enumerated (EnumType.STRING)
    @Column (name = "status", nullable = false)
    private SessionStatus status;
    
    @Column (name = "selected_time", nullable = false)
    private LocalDateTime selectedTime;
    
    @ManyToOne
    @JoinColumn (name = "selected_game_id", nullable = false)
    private Game selectedGame;
    
    @NotNull
    @CreationTimestamp 
    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
