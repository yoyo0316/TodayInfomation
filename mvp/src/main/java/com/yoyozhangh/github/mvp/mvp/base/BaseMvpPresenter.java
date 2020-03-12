package com.yoyozhangh.github.mvp.mvp.base;

import android.content.Intent;
import android.os.Bundle;

import com.yoyozhangh.github.mvp.mvp.IMvpView;
import com.yoyozhangh.github.mvp.mvp.presenter.LifeCircleMvpPresenter;

/**
 * P 层的中间类
 * 泛型类
 *
 * @param <T>
 */
public abstract class BaseMvpPresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T> {


    public BaseMvpPresenter(T view) {
        super(view);
    }

    @Override
    public void onCreate(Bundle saveInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActiviytCreate(Bundle saveInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

}
