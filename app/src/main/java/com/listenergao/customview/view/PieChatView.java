package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

/**
 * 自定义简单饼状图
 * create by 19/04/08
 *
 * @author ListenerGao
 */

public class PieChatView extends View {

    private static final int RADIUS = ResourceUtil.dp2px(120);
    private Paint paint;
    private RectF rectF;
    private static final int[] COLORS = {
            Color.parseColor("#335C67"),
            Color.parseColor("#57A860"),
            Color.parseColor("#E6AF2E"),
            Color.parseColor("#FFF3B0"),
            Color.parseColor("#9E2A2B")
    };

    private static final int[] ANGLES = {40, 60, 80, 100, 80};
    private static final int PULLED_INDEX = 2;
    private static final int PULLED_LENGTH = ResourceUtil.dp2px(20);


    public PieChatView(Context context) {
        super(context);
    }

    public PieChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < COLORS.length; i++) {
            paint.setColor(COLORS[i]);
            if (i == PULLED_INDEX) {
                canvas.save();
                //偏移
                canvas.translate(
                        (float) Math.cos(Math.toRadians(currentAngle + ANGLES[i] / 2)) * PULLED_LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + ANGLES[i] / 2)) * PULLED_LENGTH
                );
            }
            canvas.drawArc(rectF, currentAngle, ANGLES[i], true, paint);
            if (i == PULLED_INDEX) {
                canvas.restore();
            }
            currentAngle += ANGLES[i];
        }
    }
}
