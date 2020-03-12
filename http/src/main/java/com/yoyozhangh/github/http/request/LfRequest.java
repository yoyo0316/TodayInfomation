package com.yoyozhangh.github.http.request;

import com.yoyozhangh.github.http.annotation.RequestMethod;
import com.yoyozhangh.github.http.parser.IParser;

import java.lang.reflect.Type;
import java.util.Map;

public class LfRequest implements IRequest {

    protected IHost host;

    protected String path;

    @RequestMethod
    protected int requestMethod;

    protected Map<String, Object> params;
    
    protected Type type;
    protected IParser resultParser;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;

    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return type;
    }
}
