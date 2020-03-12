package com.yoyozhangh.github.task;

public interface ITaskCallback<Result> {

    void onComplete(Result o);

    void onException(Throwable throwable);
}
