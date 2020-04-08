package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.yoyozhangh.github.player.PlayService;
import com.yoyozhangh.github.player.source.RawPlayerSource;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BasePresenter;
import com.yoyozhangh.github.todayinfomation.base.helper.ContextHelper;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If.IPlayServiceContract;

public class PlayServicePresenter extends BasePresenter<IPlayServiceContract.Iview> implements IPlayServiceContract.IPresenter {

    PlayService playService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IOC 数据回调 和 service 连接成功后调用
            PlayService.PlayBinder binder = (PlayService.PlayBinder) service;
            playService = binder.getService();
            playOrPaused();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 和 service 断开后调用
            if (playService != null) {
                playService.unbindService(mConnection);
                playService = null;
            }
        }
    };

    public PlayServicePresenter(IPlayServiceContract.Iview view) {
        super(view);
    }


    @Override
    public void bindService(Context context) {
        if (playService != null) {
            playOrPaused();
        } else {
            Intent intent = new Intent(context, PlayService.class);
//        context.startService(intent);
            context.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        }


    }

    @Override
    public void playOrPaused() {
        if (playService != null) {
            //开启播放音乐
            playService.playOrPause(new RawPlayerSource().setPath(ContextHelper.getInstance().getApplicationContext().getPackageName(), R.raw.shaonian), ContextHelper.getInstance().getApplicationContext());
        }
    }
}
