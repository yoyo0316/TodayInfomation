package com.yoyozhangh.github.http.result;


/**
 * 所有IResponse 解析后的结果
 * 泛型接口
 *
 * @param <T>
 */
public interface IResult<T> {
    boolean isSucess();

    int getCode();

    T data();
}
