package com.yoyozhangh.github.todayinfomation.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.yoyozhangh.github.todayinfomation.splash.SplashActivity;

public class Test extends Application {


    private static SplashActivity haha;

    public static void  save(SplashActivity splashActivity){
        haha = splashActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // LeakCanary 的初始化
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }

        LeakCanary.install(this);
    }


}
