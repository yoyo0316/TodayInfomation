package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yoyozhangh.github.refresh.GodRefreshLayout;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.adapter.ZhiHuAdapter;
import com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.refresh.MeiTuanRefreshManager;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If.IShanghaiDetailContract;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShanghaiDetailContract.Iview {

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);

    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;
//    @BindView(R.id.refresh_recyclerview)
//    RecyclerView mRecyclerView;

    @Override
    public void afterBindView() {
        initRecyclerView();

        initRefreshLayout();
    }

    private void initRefreshLayout() {
        // 1，采用默认的方式
//        godRefresh.setRefreshManager();

        // 2,支持用户自定义
        godRefresh.setRefreshManager(new MeiTuanRefreshManager(mContext));

//        mPresenter.getNetData(20);
        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshListener() {
            @Override
            public void onRefreshing() {
//                mPresenter.getNetData(20);
                godRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        godRefresh.refreshOver();
                    }
                }, 2000);
            }
        });
    }

    private void initRecyclerView() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public void showData(ShanghaiDetailBean detailBean) {
//        if (mRecyclerView.getAdapter() == null) {
//            ZhiHuAdapter adapter = new ZhiHuAdapter(detailBean.result.data);
//            mRecyclerView.setAdapter(adapter);
//        }
//        godRefresh.refreshOver();
    }
}
