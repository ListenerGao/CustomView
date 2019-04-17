package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

/**
 * 自定义简单的SportsView
 * 练习ObjectAnimator动画使用
 * <p>
 * 使用方式:
 * 1.如果是自定义控件, 需要添加 setter / getter 方法
 * 2.用ObjectAnimator.ofXXX()创建ObjectAnimator对象(XXX和属性声明的类型有关, 如本例中 progress 为 int类型, 使用时就是ObjectAnimator.ofInt())
 * 3.使用start方法执行动画
 *
 * @author ListenerGao
 * @date 2019-04-17 15:24
 */
public class SportsWithAnimatorView extends View {

    private static final int RADIUS = ResourceUtil.dp2px(130);
    private Paint paint;
    private RectF bounds;
    private Rect textBounds = new Rect();

    private int progress = 0;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public SportsWithAnimatorView(Context context) {
        super(context);
    }

    public SportsWithAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsWithAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(ResourceUtil.dp2px(20));


        bounds = new RectF();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ResourceUtil.dp2px(20));
        paint.setColor(ActivityCompat.getColor(getContext(), R.color.colorAccent));
        paint.setStrokeCap(Paint.Cap.ROUND);
        bounds.set(centerX - RADIUS, centerY - RADIUS, centerX + RADIUS, centerY + RADIUS);
        canvas.drawArc(bounds, 90, progress * 3.6f, false, paint);
        paint.setStrokeCap(Paint.Cap.BUTT);


        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        String text = progress + "%";
        paint.getTextBounds(text, 0, text.length(), textBounds);
        float yOffset = (textBounds.top + textBounds.bottom) / 2;
        canvas.drawText(text, centerX, centerY - yOffset, paint);

    }
}
