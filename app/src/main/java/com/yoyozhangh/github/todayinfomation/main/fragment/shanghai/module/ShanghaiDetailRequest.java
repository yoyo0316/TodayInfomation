package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.module;

import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.annotation.RequestMethod;
import com.yoyozhangh.github.todayinfomation.base.JHRequest;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;

public interface ShanghaiDetailRequest {
    IRequest xiaohuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMethod.Get, ShanghaiDetailBean.class);
}
