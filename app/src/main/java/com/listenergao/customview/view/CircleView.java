package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;

/**
 * create on 19/04/23
 * 自定义CircleView,使其四边的padding值是一样的
 *
 * @author ListenerGao
 */
public class CircleView extends View {
    private static final int RADIUS = ResourceUtil.dp2px(80);
    private static final int PADDING = ResourceUtil.dp2px(20);
    private Paint paint;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (PADDING + RADIUS) * 2;

        //矫正宽(使用父控件给的宽高值)
        int measureWidth = resolveSize(size, widthMeasureSpec);
        int measureHeight = resolveSize(size, heightMeasureSpec);

        //resolveSize()方法, 就做了一下的事情

//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        switch (widthMode) {
//            //精确模式, 尺寸值是多少, 这个控件的长或宽就是多少.
//            case MeasureSpec.EXACTLY:
//                measureWidth = widthSize;
//                break;
//            //最大模式, 这个也就是父控件能够给出的最大的空间.
//            case MeasureSpec.AT_MOST:
//                if (size > widthSize) {
//                    measureWidth = widthSize;
//                } else {
//                    measureWidth = size;
//                }
//                break;
//            //未指定模式, 当前控件, 可以随便使用空间, 不受限制.
//            case MeasureSpec.UNSPECIFIED:
//                measureWidth = size;
//                break;
//            default:
//                break;
//        }
//
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        switch (heightMode) {
//            case MeasureSpec.EXACTLY:
//                measureHeight = heightSize;
//                break;
//            case MeasureSpec.AT_MOST:
//                if (size > heightSize) {
//                    measureHeight = heightSize;
//                } else {
//                    measureHeight = size;
//                }
//                break;
//            case MeasureSpec.UNSPECIFIED:
//                measureHeight = size;
//                break;
//            default:
//                break;
//        }

        setMeasuredDimension(measureWidth, measureHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
    }
}
