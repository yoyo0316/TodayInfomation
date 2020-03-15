package com.yoyozhangh.github.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class GodRefreshLayout extends LinearLayout {

    private BaseRefreshManager mRefreshManager;

    private Context mContext;
    private View mHeadView;
    private int mHeadViewHeight;
    private int minHeadViewHeight;//头部布局最小的高度
    private int maxHeadViewHeight;
    private RefreshListener mRefreshListener;// 正在刷新回调接口
    private RecyclerView mRecyclerView;
    private ScrollView mScrollView;

    public GodRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }


    /**
     * 开启下拉刷新 ，下拉刷新的效果 是默认的
     */
    public void setRefreshManager() {
        mRefreshManager = new DefaultRefreshManager(mContext);
        initHeadView();
    }

    /**
     * 开启下拉刷新 ，使用用户自定义的下拉刷新效果
     *
     * @param manager
     */
    public void setRefreshManager(BaseRefreshManager manager) {
        mRefreshManager = manager;
        initHeadView();
    }

    private void initHeadView() {
        setOrientation(VERTICAL);
        mHeadView = mRefreshManager.getHeadView();
        mHeadView.measure(0, 0);
        mHeadViewHeight = mHeadView.getMeasuredHeight();
        minHeadViewHeight = -mHeadViewHeight;
        maxHeadViewHeight = (int) (mHeadViewHeight * 0.3f);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeadViewHeight);
        params.topMargin = minHeadViewHeight;
        addView(mHeadView, 0, params);

    }


    private int interceptdownY;
    private int interceptdownX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                interceptdownY = (int) ev.getY();
                interceptdownX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // 确定滑动的方向 ，只有上下滑动才会触发
                int dy = (int) (ev.getY() - interceptdownY);
                int dx = (int) (ev.getX() - interceptdownX);

                //说明是上下滑动
                if (Math.abs(dy) > Math.abs(dx) && dy > 0 && handleChildViewIsTop()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //判断子 View 是否是滑动到顶端的
    private boolean handleChildViewIsTop() {
        if (mRecyclerView != null) {
            return RefreshScrollingUtil.isRecyclerViewToTop(mRecyclerView);
        }


        if (mScrollView != null) {
            return RefreshScrollingUtil.isScrollViewOrWebViewToTop(mScrollView);
        }
        return false;
    }

    // 这个方法回调时 可以获取当前viewGroup的子view
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // 获取RecycleView
        View child = getChildAt(0);
        if (child instanceof RecyclerView) {
            mRecyclerView = (RecyclerView) child;
        }

        //获取ScrollView
        if (child instanceof ScrollView) {
            mScrollView = (ScrollView) child;
        }
    }

    private int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("GodRefreshLayout", "onTouchEvent: " + event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                if (downY == 0) {
                    downY = interceptdownY;
                }
                int dy = moveY - downY;
                if (dy > 0) {
                    LayoutParams layoutParams = getHeadViewLayoutParams();
                    int topMargin = (int) Math.min(dy / 1.8f + minHeadViewHeight, maxHeadViewHeight);

                    // 这个事件的处理是为了不断回调比例，用于一些视觉效果
                    if (topMargin <= 0) {
                        // 0-1 进行变化
                        float precent = ((-minHeadViewHeight) - (-topMargin))*1.0f / (-minHeadViewHeight);
                        mRefreshManager.downRefreshPrecent(precent);
                    }

                    if (topMargin < 0 && mCurrentRefreshState != RefreshState.DOWNRERESH) {
                        mCurrentRefreshState = RefreshState.DOWNRERESH;
                        // 提示下拉刷新的一个状态
                        handleRefreshState(mCurrentRefreshState);
                    } else if (topMargin >= 0 && mCurrentRefreshState != RefreshState.RELEASEREFRESH) {
                        mCurrentRefreshState = RefreshState.RELEASEREFRESH;
                        // 提示释放刷新的状态
                        handleRefreshState(mCurrentRefreshState);
                    }

                    //阻尼效果
                    layoutParams.topMargin = topMargin;
                    mHeadView.setLayoutParams(layoutParams);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (handleEvent(event)) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean handleEvent(MotionEvent event) {
        final LayoutParams layoutParams = getHeadViewLayoutParams();
        if (mCurrentRefreshState == RefreshState.DOWNRERESH) {
            hideHeaderView(layoutParams);
        } else if (mCurrentRefreshState == RefreshState.RELEASEREFRESH) {
            // 保持刷新的一个状态
            layoutParams.topMargin = 0;
            mHeadView.setLayoutParams(layoutParams);
            mCurrentRefreshState = RefreshState.REFRESHING;
            handleRefreshState(mCurrentRefreshState);
            if (mRefreshListener != null) {
                mRefreshListener.onRefreshing();
            }

        }
        return layoutParams.topMargin > minHeadViewHeight;
    }


    // 自定义回调接口
    public interface RefreshListener {
        void onRefreshing();
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.mRefreshListener = refreshListener;
    }

    /**
     * 刷新完成后的操作
     */
    public void refreshOver() {
        hideHeaderView(getHeadViewLayoutParams());
        mRefreshManager.refreshOver();
    }

    private void hideHeaderView(final LayoutParams layoutParams) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.topMargin, minHeadViewHeight);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                mHeadView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentRefreshState = RefreshState.IDLE;
                handleRefreshState(mCurrentRefreshState);
            }
        });
        valueAnimator.start();
    }

    private LayoutParams getHeadViewLayoutParams() {
        return (LayoutParams) mHeadView.getLayoutParams();
    }

    private void handleRefreshState(RefreshState mCurrentRefreshState) {
        switch (mCurrentRefreshState) {
            case IDLE:
                mRefreshManager.idleRefresh();
                break;
            case REFRESHING:
                mRefreshManager.refreshingRefresh();
                break;
            case DOWNRERESH:
                mRefreshManager.downRefresh();
                break;
            case RELEASEREFRESH:
                mRefreshManager.releaseRefresh();
                break;
            default:
                break;
        }
    }

    private RefreshState mCurrentRefreshState = RefreshState.IDLE;

    // 定义下拉刷新的状态，依次为 静止，下拉刷新，释放刷新，正在刷新，刷新完成
    private enum RefreshState {
        IDLE, DOWNRERESH, RELEASEREFRESH, REFRESHING, RESRESHOVER
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        super.requestDisallowInterceptTouchEvent(disallowIntercept);
//    }
}
