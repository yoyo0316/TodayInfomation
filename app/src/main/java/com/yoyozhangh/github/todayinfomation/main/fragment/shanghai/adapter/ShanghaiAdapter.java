package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private final static String TAG = "ShanghaiAdapter";

    private final ArrayList<ShanghaiBean> mData;
    private final boolean mIsHor;

    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public ShanghaiAdapter(Context context, ArrayList<ShanghaiBean> data, boolean isHor) {
        recycledViewPool = new RecyclerView.RecycledViewPool();
        mContext = context;
        mData = data;
        mIsHor = isHor;
    }


    // 创建 view 然后进行缓存
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL) {
            if (mIsHor) {
                Log.d(TAG, "onCreateViewHolder: VERTICAL");
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, parent, false);
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
            Log.d(TAG, "onBindViewHolder: VERTICAL position = " + position);
            ((ShanghaiViewHolder) holder).mTv.setText(shanghaiBean.getmDec());
            ((ShanghaiViewHolder) holder).mImageView.setVisibility(shanghaiBean.isShowImg() ? View.VISIBLE : View.GONE);
            ((ShanghaiViewHolder) holder).itemView.setTag(position);
        } else if (holder instanceof ShanghaiViewHolderRV) {
            ((ShanghaiViewHolderRV) holder).mRecyclerView.setAdapter(new ShanghaiAdapter(mContext, shanghaiBean.getData(), false));
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
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) view.getTag();
                    Toast.makeText(mContext, "我被点击了 position=" + position, Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    //缓存view 内存友好设计
    public class ShanghaiViewHolderRV extends RecyclerView.ViewHolder {

        public RecyclerView mRecyclerView;

        public ShanghaiViewHolderRV(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.item_shanghai_rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setRecycledViewPool(recycledViewPool);
        }
    }
}
