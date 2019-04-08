package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

/**
 * 自定义简单仪表盘
 * create on 19/04/08
 *
 * @author ListenerGao
 */
public class DashboardView extends View {

    private static final int RADIUS = ResourceUtil.dp2px(120);
    private static final int ANGLE = 120;
    /**
     * 指针长度
     */
    private static final int LENGTH = ResourceUtil.dp2px(90);
    private Paint mPaint;
    private RectF mRectF;
    private Path mDash;
    Path path;
    PathDashPathEffect pathDashPathEffect;
    private PathMeasure pathMeasure;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        path = new Path();

        mDash = new Path();
        mDash.addRect(0, 0, ResourceUtil.dp2px(2), ResourceUtil.dp2px(10), Path.Direction.CCW);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        mRectF = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        //记录原图形画的路径的长度, 用于画刻度时, 均匀计算出每个间距
        path.addArc(mRectF, 90 + ANGLE / 2, 360 - ANGLE);
        pathMeasure = new PathMeasure(path, false);

        pathDashPathEffect = new PathDashPathEffect(mDash, (pathMeasure.getLength() - ResourceUtil.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * oval       弧形所在的椭圆的位置
         * startAngle 弧形起始角度(X轴的正向, 即正右的方向,是0度的位置;顺时针为正角度,逆时针为负角度
         * sweepAngle 弧形划过的角度
         * userCenter 表示是否连接到圆心, 如果连接到圆心就是扇形, 否则就是弧形
         */
        //画原图形
        canvas.drawArc(mRectF, 90 + ANGLE / 2, 360 - ANGLE, false, mPaint);

        //画刻度
        mPaint.setPathEffect(pathDashPathEffect);
        canvas.drawArc(mRectF, 90 + ANGLE / 2, 360 - ANGLE, false, mPaint);
        mPaint.setPathEffect(null);

        //画指针
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
        canvas.drawLine(getWidth() / 2,
                getHeight() / 2,
                //toRadians 角度转弧度
                getWidth() / 2 + (float) Math.cos(Math.toRadians(getAngleForDark(5))) * LENGTH,
                getHeight() / 2 + (float) Math.sin(Math.toRadians(getAngleForDark(5))) * LENGTH,
                mPaint);
    }

    /**
     * 获取指针的角度
     *
     * @param mark 指针位置
     * @return
     */
    private float getAngleForDark(int mark) {
        return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * mark;
    }
}
