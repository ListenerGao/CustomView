package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.listenergao.customview.utils.ResourceUtil;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * create on 19/04/23
 * <p>
 * 自定义颜色随机, 字体大小随机的TextView
 *
 * @author ListenerGao
 */
public class ColorTextView extends AppCompatTextView {

    private static final int[] COLORS = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };
    private static final int[] TEXT_SIZES = {
            12, 16, 20
    };
    private static final int X_PADDING = ResourceUtil.dp2px(16);
    private static final int Y_PADDING = ResourceUtil.dp2px(8);
    private static final int CONNER_RADIUS = ResourceUtil.dp2px(6);
    private Paint paint;

    private RectF bounds;


    public ColorTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bounds = new RectF();

        Random random = new Random();
        setTextColor(Color.WHITE);
        setTextSize(TEXT_SIZES[random.nextInt(TEXT_SIZES.length)]);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(COLORS[random.nextInt(COLORS.length)]);

        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bounds.set(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(bounds, CONNER_RADIUS, CONNER_RADIUS, paint);
        super.onDraw(canvas);

    }
}
