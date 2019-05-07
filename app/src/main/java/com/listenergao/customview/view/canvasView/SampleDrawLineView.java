package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;

/**
 * create on 2019/05/05
 *
 * @author ListenerGao
 */
public class SampleDrawLineView extends View {

    private Paint paint;
    private float[] lines;

    public SampleDrawLineView(Context context) {
        this(context, null);
    }

    public SampleDrawLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ResourceUtil.dp2px(14));
        paint.setStrokeCap(Paint.Cap.ROUND);

        //三条线坐标点
        lines = new float[]{
                100, 500, 600, 500,
                350, 500, 350, 1000,
                100, 1000, 600, 1000
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画单条线
        canvas.drawLine(50, 50, 400, 400, paint);

        paint.setStrokeCap(Paint.Cap.BUTT);
        //画多条线
        canvas.drawLines(lines, paint);

    }
}
