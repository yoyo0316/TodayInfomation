package com.yoyozhangh.github.todayinfomation.splash;

import android.util.Log;

import com.yoyozhangh.github.mvp.mvp.base.BaseMvpPresenter;

/**
 * Presenter 层
 */
public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter {
    private CustomCountDownTimer timer;

    private final static String TAG = "SplashTimerPresenter";

    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


    public void initTimer() {

        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + " 秒");
//                mTVTimer.setText(time + " 秒");
            }

            @Override
            public void onFinsh() {
                getView().setTvTimer("跳过");
//                mTVTimer.setText("跳过");
            }
        });
        timer.start();
    }

    public void cancle() {
        timer.cancle();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cancle();
        Log.e(TAG, "onDestroy: ");
    }


    /**
     * 防止空指针
     *
     * @return
     */
    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }


}
