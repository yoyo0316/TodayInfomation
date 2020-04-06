package com.yoyozhangh.github.todayinfomation.base.tools;

import android.view.View;

public class DoubleClickListener implements View.OnClickListener {
    private long old;
    private View.OnClickListener mOnClickListener;

    public DoubleClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        long now = System.currentTimeMillis();
        if (now - old > 1000) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(v);
            }
        }
        old = now;
    }
}
