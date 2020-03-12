package com.yoyozhangh.github.todayinfomation.base;

import com.yoyozhangh.github.http.annotation.RequestMethod;
import com.yoyozhangh.github.http.parser.DefaultResultParser;
import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.request.LfRequest;

import java.lang.reflect.Type;

public class JHRequest extends LfRequest {
    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type) {

        JHRequest request = new JHRequest();
        request.host = HostManager.jhHost;
        request.path = path;
        request.requestMethod = requestMethod;
        request.type = type;
        request.resultParser = DefaultResultParser.getInstance();
        return request;

    }
}
