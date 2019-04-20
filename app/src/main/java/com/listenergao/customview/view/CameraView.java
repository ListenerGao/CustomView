package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

/**
 * create on 2019/4/14
 *
 * @author ListenerGao
 */
public class CameraView extends View {

    private static final int IMAGE_WIDTH = ResourceUtil.dp2px(200);
    private static final int IMAGE_PADDING = ResourceUtil.dp2px(100);
    private Bitmap bitmap;

    private Paint paint;

    private Camera camera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0; //45
    float flipRotation = 0; //20

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = ResourceUtil.getResourceBitmap(getResources(), R.drawable.avatar_hacher, IMAGE_WIDTH);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // z的默认值是 -8英寸  一英寸默认是72像素
        camera.setLocation(0, 0, -6 * getResources().getDisplayMetrics().density);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);

        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();

        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint);
        canvas.restore();


        canvas.save();
        //此处步骤是反着来的
        //第四步
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        //保存Camera状态，页面不可见到可见时，会重新绘制
        camera.save();
        //以轴心作为旋转点（此处轴心是坐标原点），轴心不能自己指定，需将图片移动到原点处，旋转后再移动回来
        camera.rotateX(bottomFlip);
        //第三步
        camera.applyToCanvas(canvas);
        camera.restore();
        //第二步
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        //第一步
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint);

        canvas.restore();
    }
}
