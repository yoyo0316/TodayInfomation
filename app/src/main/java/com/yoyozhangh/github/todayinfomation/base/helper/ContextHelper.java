package com.yoyozhangh.github.todayinfomation.base.helper;

import android.app.Application;
import android.content.Context;

public class ContextHelper {

    private static ContextHelper mInstance;
    private Application application;

    public static ContextHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ContextHelper();
        }
        return mInstance;
    }

    public void init(Application application) {
        this.application = application;
    }

    public Context getApplicationContext() {
        return application.getApplicationContext();
    }
}
