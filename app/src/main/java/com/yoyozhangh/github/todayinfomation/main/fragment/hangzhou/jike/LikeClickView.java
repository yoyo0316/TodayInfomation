package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou.jike;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.main.tools.SystemUtil;

/**
 * 定义view
 */
public class LikeClickView extends View {

    boolean isLike;
    Bitmap likeBitmap;
    Bitmap unlikeBitmap;
    Bitmap shininglikeBitmap;

    Paint bitmapPaint;

    int left;
    int top;

    public LikeClickView(Context context) {
        this(context, null);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 自定义属性的获取
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LikeClickView);
        isLike = typedArray.getBoolean(R.styleable.LikeClickView_is_like, false);
        typedArray.recycle();

        init();
    }

    private void init() {
        Resources resources = getResources();
        likeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected);
        unlikeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_unselected);
        shininglikeBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_messages_like_selected_shining);
        bitmapPaint = new Paint();
    }

    /**
     * 自定义 view 中 所有的尺寸都是 通过像素 px  来表示的
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap handBitmap = isLike ? likeBitmap : unlikeBitmap;
        canvas.drawBitmap(handBitmap, left, top, bitmapPaint);
        if (isLike) {
            canvas.drawBitmap(shininglikeBitmap, left + 15, 0, bitmapPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;

        int maxHeight = unlikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 20);
        int maxWidth = unlikeBitmap.getWidth() + SystemUtil.dp2px(getContext(), 30);

        // 拿到当前控件的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);


        /**
         * MeasureSpec.EXACTLY 该模式为精确模式，当我们将 layout_width 或者 layout_height
         * 设置为具体数值的时候，如 100dp 或者是 match_parent 的时候这时候系统就是调用的精确模式
         */
        if (mode != MeasureSpec.EXACTLY) {
            //测量模式未指定 如果有背景 背景多大，我们这个控件就有多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();

            if (suggestedMinimumWidth == 0) {
                measureWidth = maxWidth;
            } else {
                measureWidth = Math.min(suggestedMinimumWidth, maxWidth);
            }

            if (suggestedMinimumWidth == 0) {
                measureHeight = maxHeight;
            } else {
                measureHeight = Math.min(suggestedMinimumHeight, measureHeight);
            }
        } else {
            // 测量模式指定，跟进用户定义的大小来判断
            measureWidth = Math.min(maxWidth, widthsize);
            measureHeight = Math.min(maxHeight, heightsize);
        }

        // 调用系统 API 完成系统测量
        setMeasuredDimension(measureWidth, measureHeight);

        getPading(measureWidth, measureHeight);
    }

    private void getPading(int measureWidth, int measureHeight) {

        int bitmapWidth = likeBitmap.getWidth();
        int bitmapHeight = likeBitmap.getHeight();

        left = (measureWidth - bitmapWidth) / 2;
        top = (measureHeight - bitmapHeight) / 2;
    }


    // 当这个自定义view 从界面脱离消失的时候
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        likeBitmap.recycle();
        unlikeBitmap.recycle();
        shininglikeBitmap.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onClick();
                break;
            default:
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

    // TODO: 2020/3/14/014 待完善动画处理
    private void onClick() {
        isLike = !isLike;
        //调用 invalidate 会触发 onDraw 方法
        invalidate();
    }
}
