package com.listenergao.customview.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;

/**
 * 资源工具类
 * create on 19/04/08
 *
 * @author ListenerGao
 * <p>
 * <p>
 * 资源类注解    https://developer.android.com/studio/write/annotations.html#enum-annotations
 * 我们都知道，Android 中的资源都是通过 R 文件来访问的，R 文件通过整形值来标识不同的资源，
 * 如果一个方法需要传递一个 String 类型的资源 ID，但是，如果我们传递一个 Layout 资源 ID，
 * 或者直接传一个普通的 int 值，程序编译期间不会报错，但是在运行到这段代码的时候就会报错。
 * <p>
 * 可通过注解支持类('com.android.support:support-annotations:X.X.X')支持各种注解
 * 注:如果使用了 appcompat 库, 测无需添加 support-annotations 依赖项, 因为 appcompat 库已经依赖注解库.
 * <p>
 * 注解符号     标记类型
 * @AnimatorRes android.R.animator
 * @AnimRes android.R.anim
 * @ArrayRes android.R.array
 * @AttrRes android.R.attr
 * @BoolRes android.R.bool
 * @ColorRes android.R.color
 * @DimenRes android.R.dimen
 * @DrawableRes android.R.drawable
 * @IdRes android.R.id
 * @LayoutRes android.R.layout
 * @RawRes android.R.raw
 * @StyleableRes android.R.styleable
 * @StyleRes android.R.style
 * @XmlRes android.R.xml
 * @InterpolatorRes android.R.interpolator
 */
public class ResourceUtil {

    public static int dp2px(int dp) {
//        float density = Resources.getSystem().getDisplayMetrics().density;
//        return (int) (dp * density + 0.5f);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2dp(int px) {
//        float density = Resources.getSystem().getDisplayMetrics().density;
//        return (int) (px / density + 0.5f);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }

    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static Bitmap getResourceBitmap(@NonNull Resources res, @DrawableRes int imgResId) {
        return getResourceBitmap(res, imgResId, -1);
    }

    /**
     * 从资源文件获取Bitmap
     *
     * @param res
     * @param imgResId
     * @param width
     * @return
     */
    public static Bitmap getResourceBitmap(@NonNull Resources res, @DrawableRes int imgResId, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只获取宽高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, imgResId, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        if (width > 0) {
            options.inTargetDensity = width;
        } else {
            options.inTargetDensity = options.outWidth;
        }
        Log.d("gys", "outWidth = " + options.outWidth + "   outHeight = " + options.outHeight);
        return BitmapFactory.decodeResource(res, imgResId, options);
    }
}
