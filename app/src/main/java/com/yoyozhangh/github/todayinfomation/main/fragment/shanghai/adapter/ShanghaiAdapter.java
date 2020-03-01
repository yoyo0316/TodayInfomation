package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiBean;

import java.util.ArrayList;


/**
 * 适配器设计模式
 */
public class ShanghaiAdapter extends RecyclerView.Adapter {


    private final ArrayList<ShanghaiBean> mData;

    private Context mContext;

    public ShanghaiAdapter(Context context, ArrayList<ShanghaiBean> data) {
        mContext = context;
        mData = data;
    }


    // 创建 view 然后进行缓存
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, parent,false);
            ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(view);
            return viewHolder;
        } else if (viewType == ShanghaiBean.IShanghaiItemType.HORIZANTAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment_rv, null);
            ShanghaiViewHolderRV viewHolder = new ShanghaiViewHolderRV(view);
            return viewHolder;
        }
        return null;
    }


    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShanghaiBean shanghaiBean = mData.get(position);
        if (holder instanceof ShanghaiViewHolder) {
            ((ShanghaiViewHolder) holder).mTv.setText(shanghaiBean.getmDec());
            ((ShanghaiViewHolder) holder).mImageView.setVisibility(shanghaiBean.isShowImg() ? View.VISIBLE:View.GONE);
        } else if (holder instanceof ShanghaiViewHolderRV) {

            ((ShanghaiViewHolderRV) holder).mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            ((ShanghaiViewHolderRV) holder).mRecyclerView.setAdapter(new ShanghaiAdapter(mContext, shanghaiBean.getData()));
        }


    }

    // 条目数量
    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getmItemType();
    }

    //缓存view 内存友好设计
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public TextView mTv;
        public ImageView mImageView;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mImageView = itemView.findViewById(R.id.item_shanghai_iv);
        }
    }


    //缓存view 内存友好设计
    public class ShanghaiViewHolderRV extends RecyclerView.ViewHolder {

        public RecyclerView mRecyclerView;

        public ShanghaiViewHolderRV(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.item_shanghai_rv);
        }
    }
}
