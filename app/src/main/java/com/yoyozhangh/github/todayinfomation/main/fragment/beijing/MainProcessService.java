package com.yoyozhangh.github.todayinfomation.main.fragment.beijing;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class MainProcessService extends Service {


    public static final int SHANGHAIDETAIL = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHANGHAIDETAIL:
                    Messenger replyTo = msg.replyTo;
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("processDec", ProcessDataTest.getInstance().getProcessDec());
                    message.setData(bundle);
                    try {
                        replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    // service  跨进程通信的原理就是这个Messenger
    private Messenger messenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
