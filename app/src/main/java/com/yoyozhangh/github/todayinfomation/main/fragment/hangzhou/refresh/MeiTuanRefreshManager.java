package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yoyozhangh.github.refresh.BaseRefreshManager;
import com.yoyozhangh.github.todayinfomation.R;

public class MeiTuanRefreshManager extends BaseRefreshManager {

    ImageView mImageView;

    public MeiTuanRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeadView() {
        View inflate = mLayoutInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);

        mImageView = (ImageView) inflate.findViewById(R.id.loading);
        return inflate;
    }

    // 下拉刷新，只会回调一次
    @Override
    public void downRefresh() {

    }

    // 释放刷新 会变成美团的吉祥物
    @Override
    public void releaseRefresh() {
        mImageView.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void idleRefresh() {
        mImageView.setScaleX(0);
        mImageView.setScaleY(0);
    }

    @Override
    public void refreshingRefresh() {
        mImageView.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void refreshOver() {

    }

    @Override
    public void downRefreshPrecent(float precent) {
        Log.e("MeiTuanRefreshManager", "downRefreshPrecent: " + precent);
        mImageView.setScaleX(precent);
        mImageView.setScaleY(precent);
    }
}
