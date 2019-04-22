package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.listenergao.customview.utils.ResourceUtil;

import androidx.annotation.RequiresApi;

/**
 * 自定义View之测量文字尺寸类
 * create on 19/04/10
 *
 * @author ListenerGao
 */
public class CustomMeasureTextView extends View {

    private static final String[] TEXTS = {"ListenerGao", "AaQLi文字测量", "การวัดข้อความ", "Hello ListenerGao"};
    private Paint paint;
    private Rect bounds;

    public CustomMeasureTextView(Context context) {
        super(context);
    }

    public CustomMeasureTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMeasureTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(ResourceUtil.sp2px(18));
        bounds = new Rect();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         * getFontSpacing() 获取推荐的行距, 即推荐的两行文字的baseline的距离.
         * 这个值是系统根据文字的字体和字号自动计算的,它的作用是当你要手动绘制多行文字(而不是使用StaticLayout)
         * 的时候, 可以在换行的时候给y坐标加上这个值来下移文字
         */
        canvas.drawText(TEXTS[0], 100, 100, paint);
        canvas.drawText(TEXTS[1], 100, 100 + paint.getFontSpacing(), paint);
        canvas.drawText(TEXTS[2], 100, 100 + paint.getFontSpacing() * 2, paint);

        //详解:https://hencoder.com/ui-1-3/
        //获取Paint的FontMetrics
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ascent = fontMetrics.ascent;
        Log.d("gys", "ascent= " + ascent);
        float descent = fontMetrics.descent;
        Log.d("gys", "descent= " + descent);
        float top = fontMetrics.top;
        Log.d("gys", "top= " + top);
        float bottom = fontMetrics.bottom;
        Log.d("gys", "bottom= " + bottom);
        float leading = fontMetrics.leading;
        Log.d("gys", "leading= " + leading);

        Log.d("gys", "font spacing = " + (bottom - top + leading));
        Log.d("gys", "font spacing2 = " + paint.getFontSpacing());


        //获取文字显示的范围
        paint.setStyle(Paint.Style.FILL);
        float offsetX = 100;
        float offsetY = 360;
        canvas.drawText(TEXTS[0], offsetX, offsetY, paint);
        /*
         * text 测量的文字
         * start 和 end 分别是文字的起始位置和结束位置
         * bounds 是存储文字显示范围的对象, 方法在测算完成之后会把结果写进 bounds
         */
        paint.getTextBounds(TEXTS[0], 0, TEXTS[0].length(), bounds);
        bounds.left += offsetX;
        bounds.top += offsetY;
        bounds.right += offsetX;
        bounds.bottom += offsetY;
        // 文字宽度
        Log.d("gys", "textWidth-1 = " + (bounds.right - bounds.left));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        canvas.drawRect(bounds, paint);

        //测量文字占用的宽度并返回
        paint.setTextSize(ResourceUtil.sp2px(18));
        canvas.drawText(TEXTS[0], 100, 500, paint);
        float textWidth = paint.measureText(TEXTS[0]);
        Log.d("gys", "textWidth-2 = " + textWidth);
        canvas.drawLine(100, 500, 100 + textWidth, 500, paint);

        //获取字符中每个字符的宽度, 并把结果填入参数widths
        //这相当于measureText()的一个快捷方式, 它的计算等价于对每个字符串中的每个字符分别调用measureText()
        float width = 0.0f;
        canvas.drawText(TEXTS[0], 100, 600, paint);
        float[] widths = new float[TEXTS[0].length()];
        paint.getTextWidths(TEXTS[0], widths);
        for (int i = 0; i < widths.length; i++) {
            width += widths[i];
        }
        Log.d("gys", "textWidth-3 = " + width);


        int measuredCount;
        float[] measuredWidth = {0};
        //宽度上限 300 (不够用, 截断)
        //breakText() 该方法可以用于多行文字的折行计算
        //breakText() 的返回值是截取的文字个数(如果文字没有超限,则是文字的总个数)
        //参数 text 是要测量的文字, measureForwards 表示文字的测量方向(true 表示由左向右测量)
        //maxWidth 是给出的宽度上限, measuredWidth 是用于接收数据, 方法测量完成后会把截取的文字宽度赋值给 measuredWidth[0].
        measuredCount = paint.breakText(TEXTS[3], 0, TEXTS[3].length(), true, 300, measuredWidth);
        Log.d("gys", "measuredCount = " + measuredCount);
        Log.d("gys", "measuredWidth = " + measuredWidth[0]);
        canvas.drawText(TEXTS[3], 0, measuredCount, 100, 700, paint);
        //宽度上限 600 (够用, 不截断)
        measuredCount = paint.breakText(TEXTS[3], 0, TEXTS[3].length(), true, 500, measuredWidth);
        Log.d("gys", "measuredCount = " + measuredCount);
        Log.d("gys", "measuredWidth = " + measuredWidth[0]);
        canvas.drawText(TEXTS[3], 0, measuredCount, 100, 700 + paint.getFontSpacing(), paint);

        // 光标相关
        paint.setColor(Color.BLACK);
        int length = TEXTS[0].length();
        float advance = paint.getRunAdvance(TEXTS[0], 0, length, 0, length, true, length);
        Log.d("gys", "advance = " + advance);
        float offsetX1 = 100;
        float offsetY1 = 820;
        canvas.drawText(TEXTS[0], offsetX1, offsetY1, paint);
        canvas.drawLine(offsetX1 + advance, offsetY1 - 40, offsetX1 + advance, offsetY1 + 10, paint);

    }
}
