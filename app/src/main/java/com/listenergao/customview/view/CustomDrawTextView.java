package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

import java.util.Locale;

import androidx.annotation.RequiresApi;

/**
 * 自定义view之drawText()文字的绘制
 * create by 19/04/10
 *
 * @author ListenerGao
 */
public class CustomDrawTextView extends View {

    private static final String TEXT = "ListenerGao";
    private static final String TEXT_CHINESE = "雨骨底条今直沿微写";
    Paint paint;

    public CustomDrawTextView(Context context) {
        super(context);
    }

    public CustomDrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置字体的大小
        paint.setTextSize(ResourceUtil.sp2px(20));
        //设置字体
        paint.setTypeface(Typeface.SERIF);
        //从资源文件中获取字体样式
//        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "字体名称.ttf"));
        //是否使用伪粗体(并不是通过选用更高weight的字体让文字变粗, 而是通过程序在运行时把文字给[描粗]了)
        paint.setFakeBoldText(true);
        //是否添加删除线
        paint.setStrikeThruText(true);
        /*
         * text 文本内容
         * x,y文字的坐标. 该坐标点是在文字左下角比较接近的位置
         */
        canvas.drawText(TEXT, 100, 100, paint);

        //是否添加下划线
        paint.setUnderlineText(true);
        //去除删除线
        paint.setStrikeThruText(false);
        canvas.drawText(TEXT, 100, 200, paint);

        //设置文字倾斜度(skewX<0右倾斜, skewX>0左倾斜)
        paint.setTextSkewX(-0.8f);
        canvas.drawText(TEXT, 100, 300, paint);

        //设置文字横向缩放, 也就是变胖或者变瘦
        paint.setTextScaleX(1.5f);
        //取消文字倾斜
        paint.setTextSkewX(0);
        canvas.drawText(TEXT, 100, 400, paint);

        //设置字符间距, 默认间距为0
        paint.setLetterSpacing(0.2f);
        //取消文字横向缩放
        paint.setTextScaleX(1f);
        //取消下划线
        paint.setUnderlineText(false);
        canvas.drawText(TEXT, 100, 500, paint);

        //重置画笔
        paint.reset();
        paint.setTextSize(ResourceUtil.sp2px(20));
        //使用CSS的font-feature-setting的方式设置文字
        paint.setFontFeatureSettings("smcp");
        canvas.drawText(TEXT, 100, 600, paint);

        //设置文字的对齐方式(LEFT左对齐, CENTER居中对齐, RIGHT右对齐. 默认LEFT)
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(TEXT, 540, 700, paint);


        paint.reset();
        paint.setTextSize(ResourceUtil.sp2px(16));
        //设置语言域
        paint.setTextLocale(Locale.CHINA);
        canvas.drawText(TEXT_CHINESE, 100, 800, paint);
        paint.setTextLocale(Locale.TAIWAN);
        canvas.drawText(TEXT_CHINESE, 100, 880, paint);


        //是否设置字体微调(目前手机屏幕的像素密度已经非常高了, 几乎不会出现字体尺寸小到需要hinting来修正的情况了)
//        paint.setHinting();

        paint.setLinearText(true);
        canvas.drawText(TEXT_CHINESE, 100, 960, paint);


    }
}
