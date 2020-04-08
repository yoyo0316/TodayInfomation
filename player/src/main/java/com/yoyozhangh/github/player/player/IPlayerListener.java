package com.yoyozhangh.github.player.player;

import com.yoyozhangh.github.player.state.PlayerState;

public interface IPlayerListener {
    void playerStateChanged(PlayerState state);
}
