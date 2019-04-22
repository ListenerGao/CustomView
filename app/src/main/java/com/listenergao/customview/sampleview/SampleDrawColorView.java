package com.listenergao.customview.sampleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * create on 2019/3/17
 *
 * @author ListenerGao
 */
public class SampleDrawColorView extends View {


    public SampleDrawColorView(Context context) {
        this(context, null);
    }

    public SampleDrawColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleDrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制背景颜色为 #88ff0000
        canvas.drawColor(Color.parseColor("#88ff0000"));

    }
}
