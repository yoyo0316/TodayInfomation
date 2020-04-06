package com.yoyozhangh.github.todayinfomation.base;

import android.app.Application;

import com.yoyozhangh.github.todayinfomation.base.crash.CrashProtectManager;

public class TodayInformationApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();
    }


}
