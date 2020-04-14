package com.yoyozhangh.github.ipc.result;

public interface IResult {
    boolean isSucess();

    int getCode();

    String data();
}
