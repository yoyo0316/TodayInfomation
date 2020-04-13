package com.yoyozhangh.github.todayinfomation.main.fragment.beijing;

import android.util.Log;

public class ProcessDataTest {
    private static ProcessDataTest mInstance;

    private String processDec;

    private ProcessDataTest() {
//        Log.e("ProcessDataTest", "ProcessDataTest: pid=" + android.os.Process.myPid());
    }

    public static synchronized ProcessDataTest getInstance() {
        if (mInstance == null) {
            mInstance = new ProcessDataTest();
        }
        return mInstance;
    }

    public String getProcessDec() {
        return processDec;
    }

    public void setProcessDec(String processDec) {
        this.processDec = processDec;
    }
}
