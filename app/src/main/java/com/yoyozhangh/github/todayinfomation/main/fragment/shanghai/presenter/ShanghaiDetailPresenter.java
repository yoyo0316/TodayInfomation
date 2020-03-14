package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.presenter;

import android.util.Log;

import com.yoyozhangh.github.http.result.IResult;
import com.yoyozhangh.github.todayinfomation.base.BasePresenter;
import com.yoyozhangh.github.todayinfomation.base.JHTask;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If.IShanghaiDetailContract;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.module.ShanghaiDetailHttpTask;

public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.Iview> implements IShanghaiDetailContract.IPresenter {


    public ShanghaiDetailPresenter(IShanghaiDetailContract.Iview view) {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.Iview getEmptyView() {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData(int pageSize) {

        // 1,数据结果的解析怎么来做
        // 2,相同任务的去重工作


//        for (int i = 0; i < 20; i++) {
//            final int finalI = i;
//        submitTask(new LfTask() {
//
//            // 一定要回调在主线程
//            @Override
//            public void onComplete(Object o) {
//                //获取网络结果
////                Log.e("getNetData", "onComplete: " + Thread.currentThread().getName());
//
//                Log.e("getNetData", "onComplete : " + o.toString());
//
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                Log.e("getNetData", "onException: " + throwable);
//
//            }
//
//            // 运行于子线程
//            @Override
//            public Object onBackground() {
//                Log.e("getNetData", "onBackground: " + Thread.currentThread().getName());
//                IResult desc = new ShanghaiDetailHttpTask().getXiaohuaList("desc", "1", "2");
//                return desc;
//
//
////                Response response = (Response) new ShanghaiDetailHttpTask().getXiaohuaList("desc", "1", "2");
////                String strresponse = null;
////                try {
////                    strresponse = response.body().string();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////                return strresponse;
//
//            }
//        });
//        }


        /**
         * 架构师的必备条件
         * 1，合理利用继承关系
         * 2，合理利用抽象编程
         * 3，合理利用泛型传递数据
         * 4，合理利用一些设计模式 比如 OKhttpCall 使用静态代理
         * 5，
         */
        submitTask(new JHTask<ShanghaiDetailBean>() {
            @Override
            public IResult<ShanghaiDetailBean> onBackground() {
                return new ShanghaiDetailHttpTask<ShanghaiDetailBean>().getXiaohuaList("desc", "1", pageSize + "");
            }

            @Override
            public void onSucess(IResult<ShanghaiDetailBean> t) {
                ShanghaiDetailBean detailBean = t.data();

                Log.e("getNetData", "onSucess: " + detailBean.toString());

                getView().showData(detailBean);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        // 取消 task 
        // TODO: 2020/3/10/010  
    }
}
