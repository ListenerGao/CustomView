package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * create on 2019/3/10
 *
 * @author ListenerGao
 */
public class CustomView extends View {

    private Paint mPaint;


    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        // 设置绘制模式
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //设置线条宽度
//        mPaint.setStrokeWidth(100);
        //设置画笔颜色
        mPaint.setColor(Color.BLACK);
        //设置抗锯齿开关(开启抗锯齿是让文字和图片的边缘更平滑)
        mPaint.setAntiAlias(true);

    }

    /**
     * 自定义绘制的方式是重写绘制方法，其中最常用的就是onDraw方法，
     * 绘制的关键是Canvas的使用，
     * Canvas的绘制类方法：drawXXX(), (关键参数：Paint)
     * Canvas的辅助类方法：范围裁切和几何变换
     * 使用不同的绘制顺序来控制图层遮盖关系
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
//        canvas.drawCircle(600, 50, 50, mPaint);

        //绘制四个圆点
//        float[] points = {0, 0, 150, 150, 300, 150, 150, 300, 300, 300};
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeWidth(100);
//        canvas.drawPoints(points, 2, 8, mPaint);

        //绘制椭圆
//        mPaint.setStrokeWidth(1);
//        RectF rectF = new RectF(50, 50, 350, 200);
//        canvas.drawOval(rectF, mPaint);

        //批量画线
//        mPaint.setStrokeWidth(20);
//        float[] lines = {50, 50, 450, 50, 250, 50, 250, 250, 50,250, 450, 250};
//        canvas.drawLines(lines, 0, 12, mPaint);

        //绘制圆角矩形
//        RectF rectF = new RectF(100, 500, 600, 200);
//        canvas.drawRoundRect(rectF, 60, 60, mPaint);

        //drawArc()是使用一个椭圆表示弧形
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(200, 100, 800, 500);
        //绘制扇形
        canvas.drawArc(rectF, -110, 100, true, mPaint);
        //绘制弧形
        canvas.drawArc(rectF, 20, 140, false, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制不封口的弧形
        canvas.drawArc(rectF, 180, 60, false, mPaint);


    }
}
