package com.listenergao.customview.sampleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.listenergao.customview.R;

import androidx.annotation.Nullable;

/**
 * create on 2019/3/22
 *
 * 使用drawRect画矩形
 * @author ListenerGao
 */
public class SampleDrawRectView extends View {

    private Paint mPaint;

    public SampleDrawRectView(Context context) {
        this(context, null);
    }

    public SampleDrawRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d("gys", "init......");
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制实心矩形（长宽一样时就是正方形了）
        RectF rectF = new RectF(100, 100, 500, 500);
        canvas.drawRect(rectF, mPaint);

        // 绘制空心矩形
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);
        RectF rectF1 = new RectF(650, 100, 1000, 300);
        canvas.drawRect(rectF1, mPaint);


    }
}
