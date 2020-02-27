package com.yoyozhangh.github.todayinfomation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yoyozhangh.github.todayinfomation.mvp.IMvpView;
import com.yoyozhangh.github.todayinfomation.mvp.MvpControler;

public class LifeCircleMvpFragment extends Fragment implements IMvpView {


    private MvpControler mvpControler;

    @Override
    public MvpControler getMvpControler() {
        if (this.mvpControler == null) {
            mvpControler = new MvpControler();
        }
        return mvpControler;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onCreate(savedInstanceState, null, bundle);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MvpControler mvpControler = getMvpControler();
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (mvpControler != null) {
            mvpControler.onCreate(savedInstanceState, null, bundle);
            mvpControler.onActiviytCreate(savedInstanceState, null, bundle);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onViewDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onSaveInstanceState(outState);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpControler mvpControler = getMvpControler();
        if (mvpControler != null) {
            mvpControler.onActivityResult(requestCode, resultCode, data);
        }
    }

}
