package com.yoyozhangh.github.todayinfomation.base;

import android.app.Application;

import com.yoyozhangh.github.todayinfomation.base.crash.CrashProtectManager;
import com.yoyozhangh.github.todayinfomation.base.helper.ContextHelper;

public class TodayInformationApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();

        // 全局的Context 获取类
        ContextHelper.getInstance().init(this);
    }


}
