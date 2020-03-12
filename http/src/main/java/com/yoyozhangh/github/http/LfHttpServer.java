package com.yoyozhangh.github.http;

import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.result.IResult;

import java.util.Map;

public class LfHttpServer {

    protected <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        return HttpHelper.execute(request, params);
    }
}
