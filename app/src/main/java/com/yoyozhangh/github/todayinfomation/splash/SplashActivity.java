package com.yoyozhangh.github.todayinfomation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yoyozhangh.github.todayinfomation.base.BaseActivity;
import com.yoyozhangh.github.todayinfomation.main.MainActivity;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview {


    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.skip)
    TextView mTVTimer;
    ISplashActivityContract.IPresenter mTimerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void afterBindView() {
        initTimerPresenter();
        initListener();
        initVideo();
    }

    private void initTimerPresenter() {
        mTimerPresenter = new SplashTimerPresenter(this);
        mTimerPresenter.initTimer();
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
                finish();
            }
        });
    }


    @Override
    public void setTvTimer(String s) {
        mTVTimer.setText(s);
    }
}