package com.groovehub.game.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_data")
public class GameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameDataId;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(columnDefinition = "TEXT")
    private String data;

    private LocalDateTime createdAt;

    public Long getGameDataId() {
        return gameDataId;
    }

    public void setGameDataId(Long gameDataId) {
        this.gameDataId = gameDataId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

enum GameStatus {
    NOT_STARTED, IN_PROGRESS, COMPLETED
}
