package com.yoyozhangh.github.todayinfomation;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yoyozhangh.github.todayinfomation.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;


public class BaseActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);

        if (annotation != null) {
            int mainlayoutId = annotation.mainlayoutid();
            if (mainlayoutId > 0) {
                setContentView(mainlayoutId);
                ButterKnife.bind(this);

            } else {
                throw new RuntimeException("mainlayoutId < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }
}
