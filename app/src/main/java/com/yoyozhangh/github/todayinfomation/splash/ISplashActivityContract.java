package com.yoyozhangh.github.todayinfomation.splash;

import com.yoyozhangh.github.todayinfomation.mvp.ILifeCircle;
import com.yoyozhangh.github.todayinfomation.mvp.IMvpView;
import com.yoyozhangh.github.todayinfomation.mvp.MvpControler;

public interface ISplashActivityContract {
    interface Iview extends IMvpView{
        void setTvTimer(String timer);
    }

    interface  IPresenter extends ILifeCircle{
        void initTimer();
    }

    Iview emptyView = new Iview() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
