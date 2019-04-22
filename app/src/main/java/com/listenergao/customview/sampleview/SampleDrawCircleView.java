package com.listenergao.customview.sampleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;

import androidx.annotation.Nullable;

/**
 * create on 2019/3/17
 *
 * @author ListenerGao
 */
public class SampleDrawCircleView extends View {

    private Paint mPaint;

    public SampleDrawCircleView(Context context) {
        this(context, null);
    }

    public SampleDrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        // 设置画笔样式
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 设置画笔颜色
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制实心圆
        canvas.drawCircle(300, 300, 200, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        // 绘制空心圆
        canvas.drawCircle(800, 300, 200, mPaint);

        // 设置画笔宽度
        mPaint.setStrokeWidth(30);
        canvas.drawCircle(300, 800, 200, mPaint);

    }
}
