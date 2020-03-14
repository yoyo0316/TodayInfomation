package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If;

import androidx.fragment.app.Fragment;

import com.yoyozhangh.github.mvp.mvp.ILifeCircle;
import com.yoyozhangh.github.mvp.mvp.IMvpView;
import com.yoyozhangh.github.mvp.mvp.MvpControler;
import com.yoyozhangh.github.todayinfomation.main.IMainActivityContract;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;

public interface IShanghaiDetailContract {

    interface Iview extends IMvpView {

        void showData(ShanghaiDetailBean detailBean);
    }

    interface IPresenter extends ILifeCircle {


        void getNetData(int pageSize);
    }

    IShanghaiDetailContract.Iview emptyView = new IShanghaiDetailContract.Iview() {

        @Override
        public void showData(ShanghaiDetailBean detailBean) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
