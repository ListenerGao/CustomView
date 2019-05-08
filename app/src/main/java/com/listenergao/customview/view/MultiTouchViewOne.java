package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;

/**
 * create on 19/05/07
 * 多点触控View
 * 接力型, 第一个手指按下了, 当有第二个手指又按下时, 第一个手指失效, 第二个手指有效.
 *
 * @author ListenerGao
 */
public class MultiTouchViewOne extends View {

    private static final int IMAGE_WIDTH = ResourceUtil.dp2px(200);
    private Paint paint;
    private Bitmap bitmap;

    private float offsetX;
    private float offsetY;
    /**
     * 图片初始偏移坐标
     * 初始化时是(0,0), 随着移动松手后,再次移动, 坐标就会发生变化
     */
    private float imageOffsetX;
    private float imageOffsetY;
    /**
     * 记录手指刚按下时的坐标
     */
    private float startDownX;
    private float startDownY;

    int trackingPointerId;

    public MultiTouchViewOne(Context context) {
        this(context, null);
    }

    public MultiTouchViewOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchViewOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bitmap = ResourceUtil.getResourceBitmap(getResources(), R.drawable.avatar, IMAGE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //触摸事件是针对View的
        //event.getActionMasked()支持多点触控事件
        //每个事件除了有坐标点x, y之外还有 index(是不断会发生变化的, 同一个事件中是不会发生变化的) 和 id(不会变化)
        //index用来遍历手指的序号, id用来跟踪记录
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //获取第一个手指按下时的id
                trackingPointerId = event.getPointerId(0);

                startDownX = event.getX();
                startDownY = event.getY();
                //记录图片的初始偏移
                imageOffsetX = offsetX;
                imageOffsetY = offsetY;
                break;

            case MotionEvent.ACTION_MOVE:
                //通过id获取index
                int index = event.findPointerIndex(trackingPointerId);
                offsetX = imageOffsetX + event.getX(index) - startDownX;
                offsetY = imageOffsetY + event.getY(index) - startDownY;
                invalidate();
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                //按下的手指的index event.getActionIndex(),此方法只在ACTION_POINTER_DOWN和ACTION_POINTER_UP时有用
                //移动时不能使用此方法获取(因为多个手指同时按下时, 都是由轻微移动的), 需要使用id来获取

                //新手指按下时, 接管事件, 记录id
                int actionIndex = event.getActionIndex();
                //通过index获取id
                trackingPointerId = event.getPointerId(actionIndex);
                startDownX = event.getX(actionIndex);
                startDownY = event.getY(actionIndex);
                //记录图片的初始偏移
                imageOffsetX = offsetX;
                imageOffsetY = offsetY;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                //非第一个手指抬起
                //看下抬起的手指, 是否是跟踪的手指
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trackingPointerId) {
                    //event.getPointerCount() 按下手指的总数 (包含正要抬起的手指)
                    //找到手指来接替事件,默认找最后一个手指
                    int newIndex;
                    if (actionIndex == event.getPointerCount() - 1) {
                        //如果抬起的就是最后一根手指, 要减2(因为event.getPointerCount() 按下手指的总数 (包含正要抬起的手指))
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }
                    trackingPointerId = event.getPointerId(newIndex);

                    startDownX = event.getX(newIndex);
                    startDownY = event.getY(newIndex);
                    //记录图片的初始偏移
                    imageOffsetX = offsetX;
                    imageOffsetY = offsetY;
                }
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }
}
