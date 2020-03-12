package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.module;

import com.yoyozhangh.github.http.LfHttpServer;
import com.yoyozhangh.github.http.result.IResult;

import java.util.HashMap;
import java.util.Map;

public class ShanghaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaohuaList(String sort, String page, String pagesize) {
        Map<String,Object> params = new HashMap<>();

        params.put("sort", sort);
        params.put("page", page);
        params.put("pagesize", pagesize);
        params.put("time", "" + System.currentTimeMillis() / 1000);
        params.put("key", "d56906ce69cedeb9ba50f39509315db4");
        return super.execute(ShanghaiDetailRequest.xiaohuaRequest,params);
    }
}
