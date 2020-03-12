package com.yoyozhangh.github.http.parser;


import com.google.gson.Gson;
import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.response.IResponse;
import com.yoyozhangh.github.http.result.IResult;
import com.yoyozhangh.github.http.result.Result;

import java.lang.reflect.Type;

public class DefaultResultParser implements IParser {
    private static DefaultResultParser mInstance;
    Gson mGson;

    private DefaultResultParser() {
        mGson = new Gson();
    }

    public static IParser getInstance() {
        if (mInstance == null) {
            mInstance = new DefaultResultParser();
        }
        return mInstance;
    }

    @Override
    public IResult parseResponse(IRequest request, IResponse response) {
        Type type = request.getType();
        String bodyStr = response.getBodyStr();

        //如何把 string 类型转换成bean 文件 ???
        // 使用 Gson  fastjson  jackson

        Object object = mGson.fromJson(bodyStr, type);
        return Result.sucess(object);
    }
}
