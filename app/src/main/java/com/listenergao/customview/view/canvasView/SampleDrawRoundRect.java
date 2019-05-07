package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * create on 19/05/05
 *
 * @author LIstenerGao
 */
public class SampleDrawRoundRect extends View {

    private Paint paint;
    private RectF bounds;

    public SampleDrawRoundRect(Context context) {
        this(context, null);
    }

    public SampleDrawRoundRect(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawRoundRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bounds = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bounds.set(100, 100, 800, 400);
        paint.setColor(ActivityCompat.getColor(getContext(), R.color.color_black));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //实心圆角矩形
        canvas.drawRoundRect(bounds, 50, 50, paint);

        paint.setColor(ActivityCompat.getColor(getContext(), R.color.colorPrimary));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ResourceUtil.dp2px(2));
        bounds.set(100, 500, 800, 800);
        //空心圆角矩形
        canvas.drawRoundRect(bounds, 50, 50, paint);
    }
}
