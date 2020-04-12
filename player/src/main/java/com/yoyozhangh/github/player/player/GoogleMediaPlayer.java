package com.yoyozhangh.github.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.yoyozhangh.github.player.source.IPlayerSource;
import com.yoyozhangh.github.player.state.PlayerState;

public class GoogleMediaPlayer implements IPlayer,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnVideoSizeChangedListener {

    private final MediaPlayer mMediaPlayer;
    private IPlayerListener mPlayerListener;

    public GoogleMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnVideoSizeChangedListener(this);

//        mMediaPlayer.isPlaying();
//        mMediaPlayer.getCurrentPosition();
//        mMediaPlayer.getDuration();

    }

    @Override
    public void release() {
        mMediaPlayer.release();
    }

    @Override
    public void prepare(Context context, IPlayerSource iPlayerSource) {
        //操作MediaPlayer
        // mediaPlayer 播放器系统资源 只能用Uri的方式
        try {
            mMediaPlayer.setDataSource(context, Uri.parse(iPlayerSource.getUrl()));
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPlayingListener(IPlayerListener listener) {
        this.mPlayerListener = listener;
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            setPlayState(PlayerState.PAUSED);
        }
    }

    @Override
    public void reStart() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            setPlayState(PlayerState.STARTED);
        }
    }


    private void setPlayState(PlayerState state) {
        if (mPlayerListener != null) {
            mPlayerListener.playerStateChanged(state);
        }
    }


    /**
     * 播放器准备好后的回调
     *
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        //初始化成功 且播放器准备好后回调
        mp.start();
        setPlayState(PlayerState.STARTED);
    }

    /**
     * 播放器如何遇到错误会回调到Java 层
     *
     * @param mp
     * @param what
     * @param extra
     * @return
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("GoogleMediaPlayer", "onError: what =" + what + " ,extra=" + extra);
        return false;
    }

    /**
     * 播放完成后的监听
     *
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        mp.release();
        setPlayState(PlayerState.IDLE);
    }

    /**
     * 播放卡顿的一些信息
     *
     * @param mp
     * @param what
     * @param extra
     * @return
     */
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }


    /**
     * 播放器的缓冲监听
     * 用于一些UI展示 ，缓冲进度条
     *
     * @param mp
     * @param percent
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    // 横竖屏切换，视频大小改变回调
    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }
}
