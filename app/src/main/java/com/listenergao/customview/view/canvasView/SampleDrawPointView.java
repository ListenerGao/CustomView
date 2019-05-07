package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * create on 2019/3/23
 *
 * @author ListenerGao
 */
public class SampleDrawPointView extends View {

    private Paint mPaint;

    public SampleDrawPointView(Context context) {
        this(context, null);
    }

    public SampleDrawPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(200);
        //可以设置点的形状，但这个方法并不是专门用来设置点的形状的，而是一个设置线条端点形状的方法。
        //端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(200, 200, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(600, 200, mPaint);

        //批量画点
        mPaint.setStrokeWidth(80);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        float[] points = {200, 600, 350, 600, 500, 600, 200, 700, 350, 700, 500, 700};
        canvas.drawPoints(points, 0, 12, mPaint);
    }
}
