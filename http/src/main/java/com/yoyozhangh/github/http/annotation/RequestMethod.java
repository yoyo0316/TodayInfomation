package com.yoyozhangh.github.http.annotation;


import androidx.annotation.IntDef;

import static com.yoyozhangh.github.http.annotation.RequestMethod.Get;
import static com.yoyozhangh.github.http.annotation.RequestMethod.Post;

/**
 * 自定义注解
 */
@IntDef({Get, Post})
public @interface RequestMethod {

    int Get = 1;
    int Post = 2;
}
