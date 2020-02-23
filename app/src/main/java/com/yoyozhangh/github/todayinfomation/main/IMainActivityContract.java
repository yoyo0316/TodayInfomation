package com.yoyozhangh.github.todayinfomation.main;

import androidx.fragment.app.Fragment;

import com.yoyozhangh.github.todayinfomation.mvp.ILifeCircle;
import com.yoyozhangh.github.todayinfomation.mvp.IMvpView;
import com.yoyozhangh.github.todayinfomation.mvp.MvpControler;

public interface IMainActivityContract {
    interface Iview extends IMvpView{
        void showFragment(Fragment fragment);

        void addFragment(Fragment fragment);

        void hideFragment(Fragment mFragment);
    }

    interface  IPresenter extends ILifeCircle{

        void initHomeFragment();
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
