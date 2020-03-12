package com.yoyozhangh.github.todayinfomation.base;

import com.yoyozhangh.github.http.result.IResult;
import com.yoyozhangh.github.http.result.IResultCallBack;
import com.yoyozhangh.github.http.result.Result;
import com.yoyozhangh.github.task.LfTask;

public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T> {


    @Override
    public void onComplete(IResult<T> iResult) {
        if (iResult != null) {
            if (iResult.isSucess()) {
                onSucess(iResult);
            }else {
                onFailed(Result.failed(Result.CODE_505));// 1次失败
            }
        } else {
            onFailed(Result.failed(Result.CODE_404));// 2次失败
        }

    }

    @Override
    public void onFailed(IResult t) {

        switch (t.getCode()){
            // 可以做成统一错误码处理
            case Result.CODE_404:
                break;
            case Result.CODE_504:
                break;
            case Result.CODE_505:
                break;
        }

    }

    @Override
    public void onException(Throwable throwable) {
        onFailed(Result.failed(Result.CODE_504));// 3次失败
    }
}
