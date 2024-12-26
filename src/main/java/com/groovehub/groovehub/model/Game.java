package com.groovehub.groovehub.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Column(nullable = false)
    private String gameType;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;


    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameData> gameData;

     public Long getGameId() {
        return gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public List<GameData> getGameData() {
        return gameData;
    }

    public void setGameData(List<GameData> gameData) {
        this.gameData = gameData;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

}
