package com.yoyozhangh.github.todayinfomation.main;

import androidx.fragment.app.Fragment;

import com.yoyozhangh.github.mvp.mvp.ILifeCircle;
import com.yoyozhangh.github.mvp.mvp.IMvpView;
import com.yoyozhangh.github.mvp.mvp.MvpControler;

public interface IMainActivityContract {

    interface Iview extends IMvpView {
        void showFragment(Fragment fragment);

        void addFragment(Fragment fragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedId();

        int getCurrentCheckedIndex();

        void replaceFragment(int currentFragmentIndex);

        int getTopPosition();
        int getBottomPosition();
    }

    Iview emptyView = new Iview() {
        @Override
        public void showFragment(Fragment fragment) {

        }

        @Override
        public void addFragment(Fragment fragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
