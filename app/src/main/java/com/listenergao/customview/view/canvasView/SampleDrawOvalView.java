package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * create on 2019/3/23
 *
 * @author ListenerGao
 */
public class SampleDrawOvalView extends View {

    private Paint mPaint;

    public SampleDrawOvalView(Context context) {
        this(context, null);
    }

    public SampleDrawOvalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //drawOval只能绘制横着的或者竖着的椭圆，不能绘制斜的，斜的需要配合几何变换来实现

        RectF rectF = new RectF(50, 50, 500, 200);
        canvas.drawOval(rectF, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        RectF rectF1 = new RectF(550, 50, 1000, 200);
        canvas.drawOval(rectF1, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF2 = new RectF(50, 250, 500, 400);
        canvas.drawOval(rectF2, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        RectF rectF3 = new RectF(550, 250, 1000, 400);
        canvas.drawOval(rectF3, mPaint);

        RectF rectF4 = new RectF(150, 450, 300, 1000);
        canvas.drawOval(rectF4, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        RectF rectF5 = new RectF(650, 450, 800, 1000);
        canvas.drawOval(rectF5, mPaint);
    }
}
