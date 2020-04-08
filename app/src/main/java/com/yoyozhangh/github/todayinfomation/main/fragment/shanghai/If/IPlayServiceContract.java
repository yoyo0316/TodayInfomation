package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If;

import android.content.Context;

import com.yoyozhangh.github.mvp.mvp.ILifeCircle;
import com.yoyozhangh.github.mvp.mvp.IMvpView;

public interface IPlayServiceContract {

    interface Iview extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {
        void bindService(Context context);

        void playOrPaused();
    }

}
