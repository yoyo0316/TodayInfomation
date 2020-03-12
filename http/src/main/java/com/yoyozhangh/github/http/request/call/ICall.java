package com.yoyozhangh.github.http.request.call;

import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.response.IResponse;
import com.yoyozhangh.github.http.result.IResult;

public interface ICall {
    IResponse execute();

    IRequest getRequest();
}
