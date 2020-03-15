package com.yoyozhangh.github.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class DefaultRefreshManager extends BaseRefreshManager {

    TextView mTVRefresh;

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeadView() {
        View view = mLayoutInflater.inflate(R.layout.util_header_layout, null, false);
        mTVRefresh = (TextView) view.findViewById(R.id.header_text);
        return view;
    }

    @Override
    public void downRefresh() {
        mTVRefresh.setText("下拉刷新");
    }

    @Override
    public void releaseRefresh() {
        mTVRefresh.setText("释放刷新");
    }

    @Override
    public void idleRefresh() {
        mTVRefresh.setText("下拉刷新");
    }

    @Override
    public void refreshingRefresh() {
        mTVRefresh.setText("正在刷新");
    }

    @Override
    public void refreshOver() {
        mTVRefresh.setText("下拉刷新");
    }

    @Override
    public void downRefreshPrecent(float precent) {

    }
}
