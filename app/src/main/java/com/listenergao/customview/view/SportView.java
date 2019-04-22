package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * create on 2019/4/13
 * <p>
 * 文字绘制-居中显示
 * 文字绘制-贴边显示
 *
 * @author ListenerGao
 */
public class SportView extends View {

    private static final int RADIUS = ResourceUtil.dp2px(150);
    private static final int WIDTH = ResourceUtil.dp2px(30);
    private static final String TEXT = "abcdg";
    private Paint paint;
    private RectF bounds;
    private Paint.FontMetrics fontMetrics;
    private Rect textBounds;

    public SportView(Context context) {
        this(context, null);
    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SportView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bounds = new RectF();
        fontMetrics = new Paint.FontMetrics();
        textBounds = new Rect();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(WIDTH);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        //绘制进度条
        paint.setColor(ActivityCompat.getColor(getContext(), R.color.colorPrimary));
        //设置点的形状，圆头（ROUND），方头（SQUARE），平头（BUTT）
        paint.setStrokeCap(Paint.Cap.ROUND);
        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        canvas.drawArc(bounds, -90, 220, false, paint);
        paint.setStrokeCap(Paint.Cap.BUTT);

        //绘制文字
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(ResourceUtil.dp2px(60));
        paint.setTextAlign(Paint.Align.CENTER);

        //测量文字高度，使其居中显示
        //getTextBounds精准测量文字宽高，getFontMetrics粗略计算文字宽高
        paint.getFontMetrics(fontMetrics);
        //计算出文字高度的一半
        float offset = (fontMetrics.descent + fontMetrics.ascent) / 2;
        Log.d("gys", "offset = " + offset);
        canvas.drawText(TEXT, getWidth() / 2, getHeight() / 2 - offset, paint);


        //文字绘制：贴边
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(ResourceUtil.dp2px(120));
        paint.getTextBounds(TEXT, 0, TEXT.length(), textBounds);
        //居左居上显示
        canvas.drawText(TEXT, 0 - textBounds.left, 0 - textBounds.top, paint);

        paint.setTextSize(ResourceUtil.dp2px(15));
        canvas.drawText(TEXT, 0, 0 - textBounds.top + paint.getFontSpacing(), paint);

    }
}
