package com.ryul.gametogether.player.DTO;

import java.util.List;

public record AddPlayerGameResponse( 
    String message,
    int addedCount,
    List<GameInfo> games
) {
    public record GameInfo(Long id, String name) {}
}
