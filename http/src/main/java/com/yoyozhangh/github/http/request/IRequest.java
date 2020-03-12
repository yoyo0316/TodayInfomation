package com.yoyozhangh.github.http.request;

import com.yoyozhangh.github.http.parser.IParser;

import java.lang.reflect.Type;
import java.util.Map;

public interface IRequest {

    /**
     * 设置参数
     *
     * @param params
     */
    void setParams(Map<String, Object> params);


    Map<String, Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

    IParser getParser();

    Type getType();
}
