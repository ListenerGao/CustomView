package com.listenergao.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * create on 19/04/23
 * 自定义正方形ImageView
 *
 * @author ListenerGao
 */
public class SquareView extends AppCompatImageView {
    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        //选取最大值作为宽高
        int size = Math.max(measuredWidth, measuredHeight);
        //保存数据
        setMeasuredDimension(size, size);

    }
}
