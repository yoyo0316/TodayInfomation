package com.yoyozhangh.github.player;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.yoyozhangh.github.player.source.IPlayerSource;
import com.yoyozhangh.github.player.state.PlayerState;

public class PlayService extends Service {


    private PlayerState mState = PlayerState.IDLE;

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


    public void playOrPause(IPlayerSource playerSource) {
        switch (mState) {
            case IDLE:
                // 初始化播放器去播放
                String url = playerSource.getUrl();

                break;
            case PREPARING:
                break;

            default:
                break;
        }
    }

}
