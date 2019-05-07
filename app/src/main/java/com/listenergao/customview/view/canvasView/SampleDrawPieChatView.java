package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;

/**
 * create on 19/05/06
 *
 * @author ListenerGao
 */
public class SampleDrawPieChatView extends View {

    private static final int[] COLORS = {
            Color.parseColor("#ef5350"),
            Color.parseColor("#ec407a"),
            Color.parseColor("#ab47bc"),
            Color.parseColor("#42a5f5"),
            Color.parseColor("#26c6da"),
            Color.parseColor("#26a69a"),
            Color.parseColor("#ffee58")
    };

    private static final int RADIUS = ResourceUtil.dp2px(100);
    private static final int OFFSET = ResourceUtil.dp2px(8);
    private Paint paint;
    private RectF bounds;

    public SampleDrawPieChatView(Context context) {
        this(context, null);
    }

    public SampleDrawPieChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawPieChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        if (bounds == null) {
            bounds = new RectF(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS);
        }

        paint.setColor(COLORS[0]);
        canvas.drawArc(bounds, 0, 10, true, paint);
        paint.setColor(COLORS[1]);
        canvas.drawArc(bounds, 10, 15, true, paint);
        paint.setColor(COLORS[2]);
        canvas.drawArc(bounds, 25, 8, true, paint);
        paint.setColor(COLORS[3]);
        canvas.drawArc(bounds, 33, 60, true, paint);
        paint.setColor(COLORS[4]);
        canvas.drawArc(bounds, 93, 87, true, paint);
        paint.setColor(COLORS[5]);
//        float maxCenterX = (float) (width / 2f + OFFSET * Math.cos(120 * Math.PI / 180));
//        float maxCenterY = (float) (height / 2f + OFFSET * Math.sin(120 * Math.PI / 180));
//        Log.d("gys", "maxCenterX = " + maxCenterX + "     maxCenterY = " + maxCenterY);
//        Log.d("gys", "x = " + width / 2 + "     y = " + height / 2);
//        Log.d("gys", "RADIUS = " + RADIUS);
        bounds.set(width / 2f - RADIUS - OFFSET, height / 2f - RADIUS - OFFSET, width / 2f + RADIUS - OFFSET, height / 2f + RADIUS - OFFSET);
        canvas.drawArc(bounds, 180, 120, true, paint);
        paint.setColor(COLORS[6]);
        bounds.set(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS);
        canvas.drawArc(bounds, 300, 60, true, paint);
    }
}
