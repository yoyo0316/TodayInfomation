package com.yoyozhangh.github.http.result;

public interface IResultCallBack<T> {
    void onSucess(IResult<T> t);

    void onFailed(IResult t);
}
