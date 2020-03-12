package com.yoyozhangh.github.http.result;


public class Result<T> implements IResult {

    public final static int CODE_200 = 200;
    public final static int CODE_404 = 404;
    public final static int CODE_504 = 504;
    public final static int CODE_505 = 505;
    protected T data;
    protected int code;
    protected boolean sucess;

    // TODO: 2020/3/11/011  
    protected String msg;


    public static IResult failed(int code) {
        Result result = new Result();
        result.code = code;
        result.sucess = false;
        return result;
    }

    public static IResult sucess(Object object) {
        Result result = new Result();
        result.code = CODE_200;
        result.data = object;
        result.sucess = true;
        return result;
    }


    @Override
    public boolean isSucess() {
        return sucess;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public T data() {
        return data;
    }
}
