package com.yoyozhangh.github.http.parser;

import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.response.IResponse;
import com.yoyozhangh.github.http.result.IResult;

public interface IParser {
    IResult parseResponse(IRequest request, IResponse response);
}
