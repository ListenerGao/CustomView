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
        //以轴心作为旋转点（此处轴心是坐标原点），轴心不能自己指定，需将图片移动到原点处，旋转后再移动回来
        camera.rotateX(45);
        // z的默认值是 -8英寸  一英寸默认是72像素
        camera.setLocation(0, 0, -6 * getResources().getDisplayMetrics().density);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-30);
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(30);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint);
        canvas.restore();


        canvas.save();
        //此处步骤是反着来的
        //第四步
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-30);
        //第三步
        camera.applyToCanvas(canvas);
        //第二步
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(30);
        //第一步
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint);

        canvas.restore();
    }
}
