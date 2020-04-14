package com.ipc.response;

import android.os.RemoteException;

import com.yoyozhangh.github.ipc.IClientInterface;
import com.yoyozhangh.github.ipc.response.BaseResponse;

public class ShanghaiDetailResponse  extends BaseResponse {

    public ShanghaiDetailResponse(String requestKey, String params, IClientInterface iClientInterface) {
        super(requestKey, params, iClientInterface);
    }
    public void shanghaiDetail(){
        try {
            mClientInterface.callBack(mRequestKey,"来自远方的祝福");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
