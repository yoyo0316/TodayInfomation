package com.yoyozhangh.github.todayinfomation;

/**
 * Presenter 层
 */
public class SplashTimerPresenter {
    private final SplashActivity mActivity;
    private CustomCountDownTimer timer;

    public SplashTimerPresenter(SplashActivity activity) {
        this.mActivity = activity;
    }

    public void initTimer() {

        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mActivity.setTvTimer(time + " 秒");
//                mTVTimer.setText(time + " 秒");
            }

            @Override
            public void onFinsh() {
                mActivity.setTvTimer("跳过");
//                mTVTimer.setText("跳过");
            }
        });
        timer.start();
    }

    public void cancle() {
        timer.cancle();
    }
}
