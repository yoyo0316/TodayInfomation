package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.adapter.ShanghaiAdapter;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDataManager;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShanghaiFragment extends BaseFragment {
    public static String TAG = "ShanghaiFragment";

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout shanghaiAppBarlayout;

    @BindView(R.id.shanghai_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListener();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        ArrayList<String> data = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            data.add("上海市欢迎您");
//        }
        mRecyclerView.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(),false));
    }

    private void initListener() {
        shanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "shanghaiAppBarlayout onOffsetChanged: appBarLayout height= [" + appBarLayout.getMeasuredHeight() + "], verticalOffset = [" + verticalOffset + "]");

                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvShanghaiWelcome.setVisibility(View.INVISIBLE);
                } else {
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
