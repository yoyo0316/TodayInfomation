package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.design;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yoyozhangh.github.todayinfomation.R;

public class BottomAnim {

    public static void show(View show) {

        // 展示的动画
        show.clearAnimation();
        Animation animation1 = AnimationUtils.loadAnimation(show.getContext(), R.anim.main_tab_traslate_show);
        show.startAnimation(animation1);
        show.setVisibility(View.VISIBLE);
    }

    public static void hide(View gone) {

        //消失动画
        gone.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(gone.getContext(), R.anim.main_tab_traslate_hide);
        gone.startAnimation(animation);
        gone.setVisibility(View.INVISIBLE);

    }
}
