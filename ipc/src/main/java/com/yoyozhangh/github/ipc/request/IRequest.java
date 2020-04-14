package com.yoyozhangh.github.ipc.request;

import com.yoyozhangh.github.ipc.CallBack;

public interface IRequest extends Comparable<IRequest> {

    void setParams(String params);

    String getParams();

    String getRequestKey();

    void addCallBack(CallBack callBack);

    CallBack getCallBack();

    long getAddTime();
}
