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
import lombok.Getter;

@Entity 
@Getter 
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
    private SessionStatus status = SessionStatus.OPEN;
    
    @Column (name = "selected_time", nullable = true)
    private LocalDateTime selectedTime;
    
    @ManyToOne
    @JoinColumn (name = "selected_game_id", nullable = true)
    private Game selectedGame;
    
    @CreationTimestamp 
    @Column (name = "created_at", nullable = true, updatable = false)
    private LocalDateTime createdAt;

    protected Session() {}

    // 세션 생성용 생성자
    public Session(Player player) {
        this.host = player;
    }

    public void confirm(Game game, LocalDateTime selectedTime) {
        if (this.status != SessionStatus.OPEN) {
            throw new IllegalStateException("확정할 수 없는 세션 상태입니다.");
        }
        this.selectedGame = game;
        this.selectedTime = selectedTime;
        this.status = SessionStatus.CONFIRMED;
    }

}
