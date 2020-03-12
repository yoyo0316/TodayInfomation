package com.yoyozhangh.github.http.okhttp;

import com.yoyozhangh.github.http.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * 静态代理
 */
public class OkhttpResponse implements IResponse {


    private final Response response;

    public OkhttpResponse(Response response) {
        this.response = response;
    }

    @Override
    public String getBodyStr() {
        try {

            // 拿到流数据 写入文件 实现下载功能
            //response.body().byteStream();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
