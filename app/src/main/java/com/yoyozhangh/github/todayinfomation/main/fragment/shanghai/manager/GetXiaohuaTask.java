package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.module.ShanghaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * AsyncTask  任务执行是串行的  支持取消任务，两个线程池
 *
 *
 */

public class GetXiaohuaTask extends AsyncTask<Object,Object,Object> {



    // 运行在 子线程中
    @Override
    protected Object doInBackground(Object... objects) {
//        Object desc = new ShanghaiDetailHttpTask().getXiaohuaList("desc", "1", "2");
        Object desc = new ShanghaiDetailHttpTask().getXiaohuaList((String) objects[0], (String) objects[1], (String) objects[2]);
        return desc;
    }

    //运行在主线程，更新数据
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Response response = (Response) o;

        try {
            Log.e("GetXiaohuaTask", "onPostExecute: "+response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
