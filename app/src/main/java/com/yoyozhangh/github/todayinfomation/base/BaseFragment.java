package com.yoyozhangh.github.todayinfomation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yoyozhangh.github.mvp.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;


public abstract class BaseFragment extends LifeCircleMvpFragment {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);

        View view = null;
        if (annotation != null) {
            int mainlayoutId = annotation.mainlayoutid();
            if (mainlayoutId > 0) {
                view = initFragmentView(inflater,mainlayoutId);
                bindView(view);
                afterBindView();
            } else {
                throw new RuntimeException("mainlayoutId < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        return view;
    }

    private View initFragmentView( LayoutInflater inflater,int mainlayoutId) {
        return inflater.from(mContext).inflate(mainlayoutId, null);
    }

    // 模板方法设计模式
    public abstract void afterBindView();

    //View  的依赖注入绑定
    private void bindView(View view) {
        ButterKnife.bind(this, view);
    }
}
