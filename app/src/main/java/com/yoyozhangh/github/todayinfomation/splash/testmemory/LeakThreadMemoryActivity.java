package com.yoyozhangh.github.todayinfomation.splash.testmemory;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yoyozhangh.github.todayinfomation.R;

/**
 * 线程导致的内存泄漏
 * <p>
 * 原理：Java 中的Thread 有一个特点就是他们都是 直接被GC Root 所引用，也就是说Dalvik虚拟机对所有
 * 被激活状态的线程都是持有强引用，导致GC永远都无法回收掉这些线程对象，除非线程被手动停止并置为null
 * 或者用户直接kill 进程操作
 * <p>
 * <p>
 * 备注 ：匿名内部的Handler 也是同样的道理
 * <p>
 * 正确的写法：自定义静态内部类
 */
public class LeakThreadMemoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leakMemoryFunction();
    }

    private void leakMemoryFunction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.d("yoyo", "Thread run: ");
                }
            }
        }).start();
    }


    //******************************  正确的写法：自定义静态内部类  *****************************************************************
    private void leakMemoryFunctin() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                Log.d("yoyo", "MyThread run: ");
            }
        }
    }

}
