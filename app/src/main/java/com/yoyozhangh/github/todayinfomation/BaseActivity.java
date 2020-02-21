package com.yoyozhangh.github.todayinfomation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity {
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
