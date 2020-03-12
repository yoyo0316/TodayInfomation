package com.yoyozhangh.github.http.okhttp;

import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.request.call.ICall;
import com.yoyozhangh.github.http.response.IResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class OkHttpCall implements ICall {

    private final IRequest request;
    private Call call;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
        this.request = request;
    }

    @Override
    public IResponse execute() {

        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OkhttpResponse okhttpResponse = new OkhttpResponse(response);

        return okhttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return request;
    }
}
