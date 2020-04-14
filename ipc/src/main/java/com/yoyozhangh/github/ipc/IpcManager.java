package com.yoyozhangh.github.ipc;


import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.yoyozhangh.github.ipc.request.IRequest;
import com.yoyozhangh.github.ipc.result.IResult;
import com.yoyozhangh.github.ipc.result.IpcResult;
import com.yoyozhangh.github.ipc.server.IpcService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class IpcManager {

    private static IpcManager mInstance;
    private final Context mContext;

    IServerlInterface mServer;

    // set集合不允许重复， TreeSet 有序集合

    // -----只针对异步请求 -------------
    private Set<IRequest> mRequests = new TreeSet<IRequest>();
    private Set<IRequest> mWaitRequests = new TreeSet<IRequest>();
    // -----只针对异步请求 -------------


    private int mConnectStatus = IConnectState.STATUS_UNBIND;
    private ServiceConnection mConnection;
    private IBinder.DeathRecipient mDeathRecipient;
    private IClientInterface.Stub mClientInterface;

    interface IConnectState {
        int STATUS_UNBIND = 0;
        int STATUS_BINDING = 1;
        int STATUS_BIND = 2;
    }

    interface IConnectCallBack {
        void callback(int connectState);
    }

    private IpcManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public synchronized static IpcManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IpcManager(context);
        }
        return mInstance;
    }

    //判断服务是否已经连接成功
    private IResult result = null;


    // TODO: 2020/4/14   有问题，待调试 
    //同步跨进程通信
    public IResult excuteSync(final IRequest request) {

        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            return IpcResult.getErrorResult();
        }

        if (mConnectStatus != IConnectState.STATUS_BIND) {
            // CountDownLatch ??? 不能使用
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            initConnect(new IConnectCallBack() {
                @Override
                public void callback(int connectState) {
                    countDownLatch.countDown();
                    IResult iResult;
                    if (connectState == IConnectState.STATUS_BIND) {
                        iResult = excute(request);
                    } else {
                        iResult = IpcResult.getErrorResult();
                    }
                    result = iResult;
                }
            });
            try {
                countDownLatch.await(5, TimeUnit.SECONDS);
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return excute(request);
    }

    // TODO: 2020/4/14  提供给客户端 建立连接的一个方法
    public void initConnect(IConnectCallBack iConnectCallBack) {
        connectService();
    }

    //异步跨进程通信
    public void excuteAsync(IRequest request, CallBack callBack) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            callBack.callBack(IpcResult.getErrorResult());
            Log.d("yyz", "excuteAsync error");
            return;
        }
        Log.d("yyz", "excuteAsync");
        // 保存 这个request
        request.addCallBack(callBack);
        mRequests.add(request);

        //判断服务是否已经连接成功
        if (mConnectStatus != IConnectState.STATUS_BIND) {
            connectService();
            mWaitRequests.add(request);
            return;
        }

        excute(request);

    }

    //建立 IPC 通信连接
    private void connectService() {
        Log.d("yyz", "connectService");
        if (mConnection == null) {
            mConnection = new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.d("yyz", "onServiceConnected: ");
                    mConnectStatus = IConnectState.STATUS_BIND;

                    mServer = IServerlInterface.Stub.asInterface(service);

                    //往服务端注入接口
                    try {
                        mServer.registerCallBack(mClientInterface);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    // Binder 通信的死亡通知，有重启逻辑 
                    try {
                        service.linkToDeath(mDeathRecipient, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    //连接成功之后 ，去把等待的数据请求发送
                    for (IRequest request : mWaitRequests) {
                        excute(request);
                    }
                    mWaitRequests.clear();

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d("yyz", "onServiceDisconnected: ");
                    mConnectStatus = IConnectState.STATUS_UNBIND;

                }
            };
        }

        if (mDeathRecipient == null) {
            mDeathRecipient = new IBinder.DeathRecipient() {

                @Override
                public void binderDied() {
                    mConnectStatus = IConnectState.STATUS_UNBIND;
                    // 针对异步请求 做 CallBack 处理
                    for (IRequest request : mRequests) {
                        request.getCallBack().callBack(IpcResult.getErrorResult());
                    }
                    mRequests.clear();
                }
            };
        }

        if (mClientInterface == null) {
            mClientInterface = new IClientInterface.Stub() {

                @Override
                public void callBack(String requestKey, String resultStr) throws RemoteException {
                    Iterator<IRequest> iterator = mRequests.iterator();
                    while (iterator.hasNext()) {
                        IRequest next = iterator.next();
                        if (TextUtils.equals(next.getRequestKey(), requestKey)) {
                            next.getCallBack().callBack(IpcResult.getSucessResult(resultStr));
                            mRequests.remove(next);
                            return;
                        }
                    }

                }
            };
        }


        Intent intent = new Intent(mContext, IpcService.class);
        mContext.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        mConnectStatus = IConnectState.STATUS_BINDING;
    }


    //实际跨进程通信的方法
    private IResult excute(IRequest request) {
        Log.d("yyz", "excute");
        if (request.getCallBack() != null) {
            try {
                mServer.excuteAsync(request.getRequestKey(), request.getParams());
            } catch (RemoteException e) {
                request.getCallBack().callBack(IpcResult.getErrorResult());
            }
        } else {
            try {
                String result = mServer.excuteSync(request.getRequestKey(), request.getParams());
                return IpcResult.getSucessResult(result);
            } catch (RemoteException e) {
                return IpcResult.getErrorResult();
            }
        }
        return IpcResult.getErrorResult();
    }

}
