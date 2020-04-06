package com.yoyozhangh.github.todayinfomation.base.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtil {

    /**
     * X轴方向的属性动画
     *
     * @param target
     * @param positionStart
     * @param positionEnd
     * @param listener
     */
    public static void startTranslationXAnima(View target, float positionStart, float positionEnd, Animator.AnimatorListener listener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "translationX", positionStart, positionEnd);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        if (listener != null){
            objectAnimator.addListener(listener);
        }
    }
}
