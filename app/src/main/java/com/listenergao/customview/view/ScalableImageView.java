package com.listenergao.customview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

/**
 * create on 2019/5/1
 *
 * @author ListenerGao
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final int IMAGE_WIDTH = ResourceUtil.dp2px(300);

    private Paint paint;
    private Bitmap bitmap;

    private float offsetX;
    private float offsetY;
    /**
     * 小边放大的倍数
     */
    private float smallScale;
    /**
     * 大边放大的倍数
     */
    private float bigScale;
    /**
     * 手势工具类
     */
    private GestureDetectorCompat detector;
    /**
     * 记录当前的scale
     */
    private boolean isBigScale;
    /**
     * 缩放的进度
     * 要对其做动画，需要重新其setter和getter方法
     */
    private float scaleFraction;
    private ObjectAnimator scaleAnimator;

    public ScalableImageView(Context context) {
        this(context, null);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bitmap = ResourceUtil.getResourceBitmap(getResources(), R.drawable.avatar, IMAGE_WIDTH);
        //可以使用此方法来简写，可以省略很多未用到的方法
//        detector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener(){});
        detector = new GestureDetectorCompat(context, this);
        //添加双击事件的监听器（可以省略，GestureDetectorCompat类中会判断， 如果实现了OnDoubleTapListener，会自动调用一下方法）
//        detector.setOnDoubleTapListener(this);

    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        offsetX = (getWidth() - bitmap.getWidth()) / 2f;
        offsetY = (getHeight() - bitmap.getHeight()) / 2f;


        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            //胖低图片
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight();
        } else {
            //瘦高图片
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //挂载手势监听器
        return detector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        float scale = isBigScale ? bigScale : smallScale;
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;

        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //true 表示接收后续事件的处理
        return true;
    }

    /**
     * 预按下
     *
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * 手指按下，并抬起，会触发此方法
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * 移动时会触发此方法
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /**
     * 长按会触发此方法
     * 使用detector.setIsLongpressEnabled(false);可以关闭长按
     *
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 手指在快速滑动时， 突然抬起会触发此方法
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /**
     * 双击时会触发此方法
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        isBigScale = !isBigScale;
        if (isBigScale) {
            //放大动画
            getScaleAnimator().start();
        }else {
            getScaleAnimator().reverse();
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
