package com.yoyozhangh.github.todayinfomation;


import android.os.Handler;

/**
 * 定时器
 */
public class CustomCountDownTimer implements Runnable {
    private final ICountDownHandler countDownHandler;
    private int countDownTme;
    private final Handler handler;

    private boolean isRun;

    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        handler = new Handler();
        this.countDownTme = time;
        this.countDownHandler = countDownHandler;
    }

    @Override
    public void run() {
        if (isRun) {
            if (countDownHandler != null) {
                countDownHandler.onTicker(countDownTme);
            }
            if (countDownTme == 0) {
                if (countDownHandler != null) {
                    countDownHandler.onFinsh();
                }
            }else {
                countDownTme = countDownTme - 1;
                handler.postDelayed(this, 1000);
            }
        }

    }

    // 开启倒计时回调
    public void start() {
        isRun = true;
        handler.post(this);
    }

    // 跳出循环 终止
    public void cancle() {
        isRun = false;
        handler.removeCallbacks(this);
    }

    // 1.实时回调这个时候的倒计时 是几秒

    // 2, 支持动态传入总时间

    // 3, 每过1s 总秒数减 1

    // 4, 总时间倒计时为0 时 回调完成的状态


    //观察者回调接口
    public interface ICountDownHandler {

        // 倒计时回调
        void onTicker(int time);

        // 完成时回调
        void onFinsh();
    }
}
