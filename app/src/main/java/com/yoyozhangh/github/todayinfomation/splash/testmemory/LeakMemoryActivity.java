package com.yoyozhangh.github.todayinfomation.splash.testmemory;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yoyozhangh.github.todayinfomation.R;

/**
 * 静态变量引用了非静态的内部类
 * 原理：静态变量一旦初始化之后，不会随着该界面的销毁会销毁，而是等该应用进程毁掉才会销毁，那么静态变量的内部类为什么会持有该activity？ 因为非静态的内部类会持有外部对象的引用。
 *
 * 如何解决呢？
 *  1，去除静态变量
 *  2，把LeakMemoryTest 变成静态内部类即可
 *
 *
*/
public class LeakMemoryActivity extends AppCompatActivity {

    static LeakMemoryTest leakMemoryTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leakMemoryFunction();
    }

    private void leakMemoryFunction() {
        leakMemoryTest = new LeakMemoryTest();
    }

    class LeakMemoryTest {
        public LeakMemoryTest() {
        }
    }
}
