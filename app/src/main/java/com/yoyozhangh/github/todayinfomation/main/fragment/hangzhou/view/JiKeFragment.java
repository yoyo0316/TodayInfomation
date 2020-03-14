package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.view;

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

@ViewInject(mainlayoutid = R.layout.fragment_jike)
public class JiKeFragment extends BaseFragment {

    @Override
    public void afterBindView() {
    }

}
