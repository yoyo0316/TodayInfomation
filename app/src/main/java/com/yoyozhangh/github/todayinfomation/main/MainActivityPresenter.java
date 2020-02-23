package com.yoyozhangh.github.todayinfomation.main;

import androidx.fragment.app.Fragment;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.main.fragment.BeijingFragment;
import com.yoyozhangh.github.todayinfomation.main.fragment.HangzhouFragment;
import com.yoyozhangh.github.todayinfomation.main.fragment.ShanghaiFragment;
import com.yoyozhangh.github.todayinfomation.main.fragment.ShenzhenFragment;
import com.yoyozhangh.github.todayinfomation.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter {

    // 当前fragment角标
    private int mCurrentFragmentIndex;

    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckId;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }


    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return null;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }

    // 切换fragment 的方法
    private void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i < mFragments.length; i++) {
            if (mCurrentFragmentIndex != i) {
                if (mFragments[i] != null) {
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment fragment = mFragments[mCurrentFragmentIndex];
        if (fragment != null) {
            addAndShowFragment(fragment);
            setCurChecked(mCurrentFragmentIndex);
        } else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }
    }

    // 记录当前 角标index
    private void setCurChecked(int index) {
        this.mCurrentFragmentIndex = index;
        switch (index) {
            case 0:
                mCurrentCheckId = R.id.rb_main_shanghai;
                break;
            case 1:
                mCurrentCheckId = R.id.rb_main_hangzhou;
                break;
            case 2:
                mCurrentCheckId = R.id.rb_main_beijing;
                break;
            case 3:
                mCurrentCheckId = R.id.rb_main_shenzhen;
                break;
        }
    }

    // 创建当前fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex) {
            case 0:
                fragment = new ShanghaiFragment();
                break;
            case 1:
                fragment = new HangzhouFragment();
                break;
            case 2:
                fragment = new BeijingFragment();
                break;
            case 3:
                fragment = new ShenzhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    /**
     * 显示 fragment
     *
     * @param fragment
     */
    private void addAndShowFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            getView().showFragment(fragment);
        } else {
            getView().addFragment(fragment);
        }

    }

    /**
     * 隐藏 fragment
     *
     * @param mFragment
     */
    private void hideFragment(Fragment mFragment) {
        if (mFragment != null && mFragment.isVisible()) {
            getView().hideFragment(mFragment);
        } else {

        }

    }
}
