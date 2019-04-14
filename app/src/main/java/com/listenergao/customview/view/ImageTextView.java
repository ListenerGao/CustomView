package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

/**
 * create on 2019/4/14
 *
 * 简单的图文混排
 *
 * @author ListenerGao
 */
public class ImageTextView extends View {

    private static final String TEXT = "The Google Play services APK contains the individual Google services and runs as a background service in the Android OS. You interact with the background service through the client library and the service carries out the actions on your behalf. An easy-to-use authorization flow is also provided to gain access to the each Google service, which provides consistency for both you and your users. The Google Play services APK is delivered through the Google Play Store, so updates to the services are not dependent on carrier or OEM system image updates. In general, devices running Android 4.1 (API level 16) or later and have the Google Play services app installed receive updates within a few days. This allows you to use the newest APIs in Google Play services and reach most of the devices in the Android ecosystem. Devices older than Android 4.1 or devices without the Google Play services app are not supported.";
    private static final int IMAGE_WIDTH = ResourceUtil.dp2px(150);
    private static final int IMAGE_PADDING = ResourceUtil.dp2px(80);

    private TextPaint textPaint;
    private Bitmap avatarBitmap = ResourceUtil.getResourceBitmap(getResources(), R.drawable.avatar, IMAGE_WIDTH);
    private float[] measuredWidth = new float[1];

    private Paint paint;
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        textPaint = new TextPaint();
        textPaint.setTextSize(ResourceUtil.dp2px(16));

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(ResourceUtil.dp2px(16));
        paint.getFontMetrics(fontMetrics);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制多行文字(当有文字和图片时，就需要使用下面的方法来绘制多行文字)
//        StaticLayout staticLayout = new StaticLayout(TEXT, textPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL,
//                1, 0, false);
//        staticLayout.draw(canvas);

        //绘制图片
        canvas.drawBitmap(avatarBitmap, getWidth() - IMAGE_WIDTH, IMAGE_PADDING, paint);

        //绘制多行文字
        int textLength = TEXT.length();
        float yOffset = paint.getFontSpacing();
        float usableWidth;
        Log.d("gys", "width = " + IMAGE_WIDTH);
        Log.d("gys", "padding = " + IMAGE_PADDING);
        for (int start = 0, breakTextCount; start < textLength; start += breakTextCount, yOffset += paint.getFontSpacing()) {
            float textTop = yOffset + fontMetrics.ascent;
            float textBottom = yOffset + fontMetrics.descent;
            Log.d("gys", "textTop = " + textTop);
            Log.d("gys", "textBottom = " + textBottom);
            if ((textTop > IMAGE_PADDING && textTop < IMAGE_WIDTH + IMAGE_PADDING) ||
                    (textBottom > IMAGE_PADDING && textBottom < IMAGE_WIDTH + IMAGE_PADDING)) {
                usableWidth = getWidth() - IMAGE_WIDTH;
                Log.d("gys", ".............");
            } else {
                usableWidth = getWidth();
            }
            breakTextCount = paint.breakText(TEXT, start, textLength, true, usableWidth, measuredWidth);
            canvas.drawText(TEXT, start, start + breakTextCount, 0, yOffset, paint);
        }
    }
}
