package com.yoyozhangh.github.player.player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.yoyozhangh.github.player.source.IPlayerSource;
import com.yoyozhangh.github.player.state.PlayerState;

public class ExoMediaPlayer implements IPlayer, Player.EventListener {

    SimpleExoPlayer mExoPlayer;

    public ExoMediaPlayer(Context context) {
        // 创建播放器
        mExoPlayer = new SimpleExoPlayer.Builder(context).build();
        mExoPlayer.addListener(this);

    }

    private IPlayerListener mPlayerListener;

    @Override
    public void release() {

    }

    @Override
    public void prepare(Context context, IPlayerSource iPlayerSource) {
        // 准备资源去播放

        DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(iPlayerSource.getResId()));
        RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(context);
        try {
            rawResourceDataSource.open(dataSpec);
        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
        }
        Uri uri = rawResourceDataSource.getUri();

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, ""));
// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
// Prepare the player with the source.
        mExoPlayer.prepare(videoSource);
        mExoPlayer.setPlayWhenReady(true);
        setPlayState(PlayerState.STARTED);
    }

    @Override
    public void setPlayingListener(IPlayerListener listener) {
        this.mPlayerListener = listener;
    }

    @Override
    public void pause() {
        mExoPlayer.setPlayWhenReady(false);
        setPlayState(PlayerState.PAUSED);
    }

    @Override
    public void reStart() {
        mExoPlayer.setPlayWhenReady(true);
        setPlayState(PlayerState.STARTED);
    }


    private void setPlayState(PlayerState state) {
        if (mPlayerListener != null) {
            mPlayerListener.playerStateChanged(state);
        }
    }
}
