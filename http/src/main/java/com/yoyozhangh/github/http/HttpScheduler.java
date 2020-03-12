package com.yoyozhangh.github.http;

import com.yoyozhangh.github.http.parser.IParser;
import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.request.call.ICall;
import com.yoyozhangh.github.http.response.IResponse;
import com.yoyozhangh.github.http.result.IResult;

public abstract class HttpScheduler {
    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call) {

        //IResponse 和 IResult 进行一个转换
        IResponse iResponse = call.execute();

        IRequest request = call.getRequest();
        IParser parser = request.getParser();

        return parser.parseResponse(request,iResponse);
    }
}
