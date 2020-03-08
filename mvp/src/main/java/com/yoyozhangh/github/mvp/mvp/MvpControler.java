package com.yoyozhangh.github.mvp.mvp;

import android.content.Intent;
import android.os.Bundle;

import com.yoyozhangh.github.mvp.mvp.presenter.LifeCircleMvpPresenter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MvpControler implements ILifeCircle {

    // 存放的是 P 层的实例
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public <T extends IMvpView> void savePresenter(LifeCircleMvpPresenter<T> tLifeCircleMvpPresenter) {
        this.lifeCircles.add(tLifeCircleMvpPresenter);
    }

    public static MvpControler newInstance() {
        return new MvpControler();
    }

    @Override
    public void onCreate(Bundle saveInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            if (intent == null){
                intent = new Intent();
            }
            if (getArguments == null){
                getArguments = new Bundle();
            }
            next.onCreate(saveInstanceState, intent, getArguments);

        }

    }

    @Override
    public void onActiviytCreate(Bundle saveInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            if (intent == null){
                intent = new Intent();
            }
            if (getArguments == null){
                getArguments = new Bundle();
            }
            next.onActiviytCreate(saveInstanceState, intent, getArguments);

        }
    }

    @Override
    public void onStart() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.onResume();
        }
    }

    @Override
    public void onPause() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.onDestroy();
        }
    }

    @Override
    public void destroyView() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.destroyView();
        }
    }

    @Override
    public void onViewDestroy() {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.onViewDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            if (intent == null){
                intent = new Intent();
            }
            next.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            if (data == null){
                data = new Intent();
            }
            next.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            if (bundle == null){
                bundle = new Bundle();
            }
            next.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            next.attachView(iMvpView);
        }
    }


}

