package com.yoyozhangh.github.player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yoyozhangh.github.player.player.IPlayer;
import com.yoyozhangh.github.player.player.IPlayerListener;
import com.yoyozhangh.github.player.player.PlayerFactory;
import com.yoyozhangh.github.player.source.IPlayerSource;
import com.yoyozhangh.github.player.state.PlayerState;

public class PlayService extends Service implements IPlayerListener {


    private PlayerState mState = PlayerState.IDLE;
    private IPlayer mPlayer;
    private PlayerFactory mPlayerFactory;

    @Override
    public void playerStateChanged(PlayerState state) {
        this.mState = state;
    }

    public class PlayBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }


    /**
     * onStartCommand 在 startService 有作用 start 多次就会调用多次
     * 一般用来给service 传递数据
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * onCreate 不管 start 还是bind  也不管调用几次，只会调用一次
     * 常用来做全局的初始化操作
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void playOrPause(IPlayerSource playerSource, Context context) {
        switch (mState) {
            case IDLE:
                // 初始化播放器去播放
                if (mPlayer != null) {
                    mPlayer.release();
                }
                if (mPlayerFactory == null) {
                    mPlayerFactory = new PlayerFactory();
                }
                if (mPlayer == null) {
                    mPlayer = mPlayerFactory.createPlayer(context);
                }
                if (mPlayer == null) {
                    // 播放器创建失败
                    Log.e("createPlayer ", ": createPlayer failed ");
                    return;
                }

                // 拿到播放器 去播放器
                mPlayer.setPlayingListener(this);
                mPlayer.prepare(context, playerSource);
                break;
            case STARTED:
                // 去暂停
                if (mPlayer != null){
                    mPlayer.pause();
                }
                break;
            case PAUSED:
                // 继续播放
                if (mPlayer != null){
                    mPlayer.reStart();
                }
                break;

            case PREPARING:
                break;

            default:
                break;
        }
    }

}
