package com.yoyozhangh.github.todayinfomation.splash;

//import com.yoyozhangh.github.annotation.MvpEmptyViewFactory;
import com.yoyozhangh.github.mvp.mvp.ILifeCircle;
import com.yoyozhangh.github.mvp.mvp.IMvpView;

public interface ISplashActivityContract {


//    @MvpEmptyViewFactory
    interface Iview extends IMvpView {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

//    Iview emptyView = new Iview() {
//        @Override
//        public void setTvTimer(String timer) {
//
//        }
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };
}
