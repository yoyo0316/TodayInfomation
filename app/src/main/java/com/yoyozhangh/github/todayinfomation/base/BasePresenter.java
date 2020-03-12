package com.yoyozhangh.github.todayinfomation.base;

import com.yoyozhangh.github.mvp.mvp.IMvpView;
import com.yoyozhangh.github.mvp.mvp.base.BaseMvpPresenter;
import com.yoyozhangh.github.task.LfTask;
import com.yoyozhangh.github.task.TaskHelper;

import java.util.ArrayList;

/**
 * 集成MVP及网络请求快捷方式
 * 泛型绑定 <T extends IMvpView>
 * @param <T>
 */

public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view) {
        super(view);
    }

    public void submitTask(LfTask task) {
        TaskHelper.submitTask(task,task);
    }
}
