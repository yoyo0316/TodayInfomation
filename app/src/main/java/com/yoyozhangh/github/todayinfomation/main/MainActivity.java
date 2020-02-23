package com.yoyozhangh.github.todayinfomation.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseActivity;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;

import butterknife.BindView;
import butterknife.OnClick;


@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview {

    IMainActivityContract.IPresenter  mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_bottom)
    FloatingActionButton facMainBottom;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;
    @BindView(R.id.rg_main_group_top)
    RadioGroup rgMainGroupTop;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.rg_main_group_bottom)
    RadioGroup rgMainGroupBottom;
    private boolean isChangeTopOrBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void afterBindView() {
        initFragment();
        changeAnime(rgMainGroupBottom,rgMainGroupTop);
    }

    private void initFragment() {
        mPresenter.initHomeFragment();
    }

    @OnClick(R.id.fac_main_bottom)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_bottom:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnime(rgMainGroupBottom,rgMainGroupTop);
                } else {
                    changeAnime(rgMainGroupTop,rgMainGroupBottom);
                }
                break;
        }
    }

    private void changeAnime(RadioGroup gone, RadioGroup show) {

        //消失动画
        gone.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_tab_traslate_hide);
        gone.startAnimation(animation);
        gone.setVisibility(View.GONE);

        // 展示的动画
        show.clearAnimation();
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.main_tab_traslate_show );
        show.startAnimation(animation1);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment);
    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,fragment);
    }

    @Override
    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment);
    }

}
