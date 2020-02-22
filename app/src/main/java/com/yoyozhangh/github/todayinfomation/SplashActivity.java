package com.yoyozhangh.github.todayinfomation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity {


    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.skip)
    TextView mTVTimer;
    SplashTimerPresenter mTimerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTimerPresenter();
        initListener();
        initVideo();

        //把初始化 Timer 及相关内容抽出到Presenter层中
//        initTimer();
    }

    private void initTimerPresenter() {
        mTimerPresenter = new SplashTimerPresenter(this);
        mTimerPresenter.initTimer();
    }

    private void initTimer() {
//        mTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
//            @Override
//            public void onTicker(int time) {
//                mTVTimer.setText(time + " 秒");
//            }
//
//            @Override
//            public void onFinsh() {
//                mTVTimer.setText("跳过");
//            }
//        });
//        mTimer.start();
    }

    private void initVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash2));
    }

    private void initListener() {

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        mTVTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimerPresenter.cancle();
    }

    public void setTvTimer(String s) {
        mTVTimer.setText(s);
    }
}
