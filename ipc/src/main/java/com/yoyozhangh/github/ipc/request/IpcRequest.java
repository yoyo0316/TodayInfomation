package com.yoyozhangh.github.ipc.request;

import androidx.annotation.NonNull;

import com.yoyozhangh.github.ipc.CallBack;

public class IpcRequest implements IRequest {

    private String mRequestKey;
    private long mTime;
    private String mParams;
    private CallBack mCallBack;


    public IpcRequest() {
        mTime = System.currentTimeMillis();
    }

    public IpcRequest(@NonNull String requestKey) {
        this.mRequestKey = requestKey;
    }

    @Override
    public void setParams(@NonNull String params) {
        this.mParams = params;
    }

    @Override
    public String getParams() {
        return mParams;
    }

    @Override
    public String getRequestKey() {
        return mRequestKey;
    }

    @Override
    public void addCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public CallBack getCallBack() {
        return mCallBack;
    }

    @Override
    public long getAddTime() {
        return mTime;
    }

    @Override
    public int compareTo(IRequest o) {
        return (int) (this.getAddTime() - o.getAddTime());
    }
}
