package com.yoyozhangh.github.todayinfomation.splash.testmemory;

import android.content.Context;

/**
 * 演示单例的内存泄漏
 * <p>
 * 单例模式传入 activity 的context
 * 解决方法：传入 application 的 context
 */
public class MemoryTest {


    private static MemoryTest mInstance;
    private final Context context;

    public MemoryTest(Context context) {
//        this.context = context;
        // 解决单例模式的泄漏
        this.context = context.getApplicationContext();
    }

    public static MemoryTest getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MemoryTest(context);
        }
        return mInstance;
    }
}
