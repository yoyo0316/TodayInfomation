package com.yoyozhangh.github.todayinfomation.main.fragment.shenzhen;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;

@ViewInject(mainlayoutid = R.layout.fragment_shenzhen)
public class ShenzhenFragment extends BaseFragment {

    @Override
    public void afterBindView() {

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if (isVisibleToUser){
//            // 加载网络数据
//        }
//        super.setUserVisibleHint(isVisibleToUser);
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("ShenzhenFragment", "onAttach: " );
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ShenzhenFragment", "onCreate: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ShenzhenFragment", "onPause: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ShenzhenFragment", "onDestroy: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ShenzhenFragment", "onDestroyView: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ShenzhenFragment", "onDetach: " );
    }
}
