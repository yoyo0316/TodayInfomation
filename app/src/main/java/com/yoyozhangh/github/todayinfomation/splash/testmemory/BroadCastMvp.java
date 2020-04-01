package com.yoyozhangh.github.todayinfomation.splash.testmemory;

import android.content.Context;

/**
 * activity fragment 被静态持有
 * 静态持有了 activity 或者 fragment 的 context
 */
public class BroadCastMvp {
    private static boolean mIsValiadte = false;
    private static Context mContext;
}
