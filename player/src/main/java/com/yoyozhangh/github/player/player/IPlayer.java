package com.yoyozhangh.github.player.player;

import android.content.Context;

public interface IPlayer {
    /**
     * 播放器释放
     */
    void release();

    void prepare(Context context, String url);

    void setPlayingListener(IPlayerListener listener);

    void pause();

    void reStart();
}
