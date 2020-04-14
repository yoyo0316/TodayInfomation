package com.yoyozhangh.github.ipc.response;

import com.yoyozhangh.github.ipc.IClientInterface;

public class BaseResponse {

    public final String mRequestKey;
    public final String mParams;
    public final IClientInterface mClientInterface;

    public BaseResponse(String requestKey, String params, IClientInterface iClientInterface) {
        this.mRequestKey = requestKey;
        this.mParams = params;
        this.mClientInterface = iClientInterface;
    }
}
