package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.base.tools.AnimationUtil;
import com.yoyozhangh.github.todayinfomation.base.tools.DoubleClickListener;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If.IPlayServiceContract;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.adapter.ShanghaiAdapter2;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.presenter.PlayServicePresenter;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShanghaiFragment extends BaseFragment implements IPlayServiceContract.Iview {
    public static String TAG = "ShanghaiFragment";

    IPlayServiceContract.IPresenter mPreseneter = new PlayServicePresenter(this);

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout shanghaiAppBarlayout;

    @BindView(R.id.shanghai_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_marquee_title)
    TextView tvMarqueeTitle;
    private boolean mIsPlaying;

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
//        mRecyclerView.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(),false));
        mRecyclerView.setAdapter(new ShanghaiAdapter2());
    }

    private void initListener() {
        shanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "shanghaiAppBarlayout onOffsetChanged: appBarLayout height= [" + appBarLayout.getMeasuredHeight() + "], verticalOffset = [" + verticalOffset + "]");

                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvShanghaiWelcome.setVisibility(View.INVISIBLE);
                    tvMarqueeTitle.setVisibility(View.INVISIBLE);
                } else {

                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                    if (mIsPlaying) {
                        tvMarqueeTitle.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        tvShanghaiWelcome.setOnClickListener(new DoubleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShanghaiWelcome.clearAnimation();
                tvMarqueeTitle.clearAnimation();
                if (mIsPlaying) {
                    // 关闭音视频的动画
                    tvMarqueeTitle.setVisibility(View.GONE);
                    AnimationUtil.startTranslationXAnima(tvShanghaiWelcome, tvShanghaiWelcome.getTranslationX(), tvShanghaiWelcome.getTranslationX() + 150, null);
                    AnimationUtil.startTranslationXAnima(tvMarqueeTitle, tvMarqueeTitle.getTranslationX(), tvMarqueeTitle.getTranslationX() + 150, null);
                    mPreseneter.playOrPaused();
                } else {
                    // 播放音视频动画
                    AnimationUtil.startTranslationXAnima(tvShanghaiWelcome, tvShanghaiWelcome.getTranslationX(), tvShanghaiWelcome.getTranslationX() - 150, null);
                    AnimationUtil.startTranslationXAnima(tvMarqueeTitle, tvMarqueeTitle.getTranslationX(), tvMarqueeTitle.getTranslationX() - 150, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            tvMarqueeTitle.setVisibility(View.VISIBLE);
                            // 启动Service 去播放后台音乐
                            mPreseneter.bindService(mContext);
                        }
                    });
                }
                mIsPlaying = !mIsPlaying;
            }
        }));
    }
}
