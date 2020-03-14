package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 适配器设计模式
 */
public class ZhiHuAdapter extends RecyclerView.Adapter {
    private final static String TAG = "ZhiHuAdapter";
    private final List<ShanghaiDetailBean.XiaohuaListBean.XiaohuaBean> mData;

    public ZhiHuAdapter( List<ShanghaiDetailBean.XiaohuaListBean.XiaohuaBean> data) {
        mData = data;
    }


    // 创建 view 然后进行缓存
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, null);
        ZhiHuViewHolder viewHolder = new ZhiHuViewHolder(view);
        return viewHolder;
    }


    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShanghaiDetailBean.XiaohuaListBean.XiaohuaBean xiaohuaBean = mData.get(position);
        Log.d(TAG, "onBindViewHolder: VERTICAL position = " + position);
        ((ZhiHuViewHolder) holder).mTv.setText(xiaohuaBean.content);
        ((ZhiHuViewHolder) holder).mImageView.setVisibility(View.GONE);
        ((ZhiHuViewHolder) holder).itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    //缓存view 内存友好设计
    public class ZhiHuViewHolder extends RecyclerView.ViewHolder {

        public TextView mTv;
        public ImageView mImageView;

        public ZhiHuViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mImageView = itemView.findViewById(R.id.item_shanghai_iv);
        }
    }

}
