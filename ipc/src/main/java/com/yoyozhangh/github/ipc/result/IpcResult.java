package com.yoyozhangh.github.ipc.result;

public class IpcResult implements IResult {

    public String data;
    public int code;
    public boolean sucess;


    public static IResult getErrorResult() {
        IpcResult result = new IpcResult();
        result.sucess = false;
        return result;
    }

    public static IResult getSucessResult(String data) {
        IpcResult result = new IpcResult();
        result.sucess = true;
        result.data = data;
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
    public String data() {
        return data;
    }
}
