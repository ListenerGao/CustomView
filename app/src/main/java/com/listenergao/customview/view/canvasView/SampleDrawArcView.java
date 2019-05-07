package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * create on 19/05/05
 *
 * @author ListenerGao
 */
public class SampleDrawArcView extends View {

    private Paint paint;
    private RectF oval;

    public SampleDrawArcView(Context context) {
        this(context, null);
    }

    public SampleDrawArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oval = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        oval.set(200, 100, 800, 500);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //X轴正向方向为0度, 顺时针旋转为正, 逆时针旋转为负
        //绘制扇形
        canvas.drawArc(oval, -110, 100, true, paint);
        //绘制弧形
        canvas.drawArc(oval, 20, 140, false, paint);

        paint.setStyle(Paint.Style.STROKE);
        //绘制不封口弧形
        canvas.drawArc(oval, 180, 60, false, paint);

    }
}
