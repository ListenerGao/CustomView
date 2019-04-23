package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 19/04/23
 * <p>
 * 自定义简单TagLayout
 *
 * @author ListenerGao
 */
public class TagLayout extends ViewGroup {

    private List<Rect> childrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthUsed = 0;
        int heightUsed = 0;
        int lineHeight = 0;
        int lineWidthUsed = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //遍历View, 计算出每个view的大小
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (widthMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth() > widthSize) {
                //换行
                lineWidthUsed = 0;
                heightUsed += lineHeight;
                //换行后需要重新测量一下
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect childBounds;
            if (childrenBounds.size() <= i) {
                childBounds = new Rect();
                childrenBounds.add(childBounds);
            } else {
                childBounds = childrenBounds.get(i);
            }
            childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(lineWidthUsed, widthUsed);
            lineHeight = Math.max(lineHeight, child.getMeasuredHeight());
        }

        int measuredWidth = widthUsed;
        heightUsed += lineHeight;
        int measuredHeight = heightUsed;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历所有view, 给每一个view进行布局,需要重写onMeasure方法,计算view的左上右下坐标
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
