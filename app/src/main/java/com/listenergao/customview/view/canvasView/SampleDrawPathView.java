package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * create on 19/05/05
 *
 * @author ListenerGao
 */
public class SampleDrawPathView extends View {

    private Paint paint;
    private RectF bounds;
    private Path path;

    public SampleDrawPathView(Context context) {
        this(context, null);
    }

    public SampleDrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        bounds = new RectF();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        bounds.set(200, 100, 800, 700);

//        //使用Path画圆
//        path.addArc(bounds, 0, 90);
//        path.addArc(bounds, 90, 180);
//        path.addArc(bounds, 180, 270);
//        path.addArc(bounds, 270, 360);

//        paint.setStyle(Paint.Style.STROKE);
//        //lineTo()的参数为绝对坐标
//        path.lineTo(100, 100);
//        //rLineTo()的参数是相对于当前位置的相对坐标
//        //当前位置:所谓当前位置,即最后一次调用画Path的方法的终点位置. 初始值为圆点(0, 0).
//        path.rLineTo(100, 0);

        //画心形
        bounds.set(200, 200, 400, 400);
        path.addArc(bounds, -225, 225);
        bounds.set(400, 200, 600, 400);
        path.arcTo(bounds, -180, 225, false);
        path.lineTo(400, 542);

        canvas.drawPath(path, paint);
    }
}
