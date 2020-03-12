package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If;

import androidx.fragment.app.Fragment;

import com.yoyozhangh.github.mvp.mvp.ILifeCircle;
import com.yoyozhangh.github.mvp.mvp.IMvpView;
import com.yoyozhangh.github.mvp.mvp.MvpControler;
import com.yoyozhangh.github.todayinfomation.main.IMainActivityContract;

public interface IShanghaiDetailContract {

    interface Iview extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {


        void getNetData();
    }

    IShanghaiDetailContract.Iview emptyView = new IShanghaiDetailContract.Iview() {

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
