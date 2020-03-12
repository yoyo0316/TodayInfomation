package com.yoyozhangh.github.http;

import com.yoyozhangh.github.http.okhttp.OkHttpHttpScheduler;
import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.request.call.ICall;
import com.yoyozhangh.github.http.result.IResult;

import java.util.Map;

public class HttpHelper {


    private volatile static HttpScheduler httpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if (httpScheduler == null) {
            synchronized (HttpHelper.class) {
                if (httpScheduler == null) {
                    httpScheduler = new OkHttpHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }

    // TODO: 2020/3/9/009  
    protected static <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler().execute(call);
    }
}
