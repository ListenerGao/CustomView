package com.listenergao.customview.view.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * create on 19/05/05
 *
 * @author ListenerGao
 */
public class SampleDrawHistogramView extends View {

    private Paint paint;
    private float[] lines;

    private RectF rectBounds;
    private Random random;

    private static final int SPACE = ResourceUtil.dp2px(8);
    private static final int RECT_WIDTH = ResourceUtil.dp2px(24);
    private float startX;


    public SampleDrawHistogramView(Context context) {
        this(context, null);
    }

    public SampleDrawHistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawHistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        startX = 100;
        lines = new float[]{
                100, 50, 100, 600,
                100, 600, 800, 600
        };
        rectBounds = new RectF();
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLines(lines, paint);

        paint.setColor(ActivityCompat.getColor(getContext(), R.color.colorPrimary));
        paint.setTextSize(ResourceUtil.dp2px(12));

        for (int i = 120; i < 760; i += SPACE + RECT_WIDTH) {
            rectBounds.set(i, random.nextInt(500) + 50, i + RECT_WIDTH, 600);
            canvas.drawRect(rectBounds, paint);
            canvas.drawText("Froyo", i, 640, paint);
        }

    }
}
