package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * create on 2019/3/15
 *
 * @author ListenerGao
 */
public class PathView extends View {

    private Paint mPaint;
    private Path mPath;


    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

        mPath = new Path();
        //心形
//        RectF rectF = new RectF(200, 200, 400, 400);
//        mPath.addArc(rectF, -225, 225);
//        RectF rectF1 = new RectF(400, 200, 600, 400);
//        mPath.arcTo(rectF1, -180, 225, false);
//        mPath.lineTo(400, 542);

        //path画线
//        mPaint.setStyle(Paint.Style.STROKE);
//        //由当前位置（0 ，0）向（100，100）画一条直线
//        mPath.lineTo(100, 100);
//        //由当前位置（100，100）向正右方向100像素的位置画一条直线
//        mPath.rLineTo(100, 0);

        //path画线
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.lineTo(100, 100);
        mPath.moveTo(200, 100);
        mPath.lineTo(200, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制出 path 描述的图形
        canvas.drawPath(mPath, mPaint);
    }
}
