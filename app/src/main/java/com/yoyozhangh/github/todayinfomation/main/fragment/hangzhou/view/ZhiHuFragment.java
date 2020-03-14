package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.view;

import android.view.animation.AnimationUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.adapter.ZhiHuAdapter;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If.IShanghaiDetailContract;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShanghaiDetailContract.Iview {

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);


    @BindView(R.id.zhihu_toolbar)
    Toolbar zhihuToolbar;
    @BindView(R.id.zhihu_app_barlayout)
    AppBarLayout zhihuAppBarlayout;
    @BindView(R.id.zhihu_recyclerView)
    RecyclerView zhihuRecyclerView;

    @Override
    public void afterBindView() {
        zhihuRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        zhihuRecyclerView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.zhihu_recyclerview_show));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShanghaiDetailBean data) {
        if (zhihuRecyclerView.getAdapter() == null){
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            zhihuRecyclerView.setAdapter(adapter);
        }
    }
}
