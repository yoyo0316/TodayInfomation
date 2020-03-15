package com.yoyozhangh.github.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseRefreshManager {


    public LayoutInflater mLayoutInflater;

    public BaseRefreshManager(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public abstract View getHeadView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void idleRefresh();

    public abstract void refreshingRefresh();

    public abstract void refreshOver();

    public abstract void downRefreshPrecent(float precent);
}
