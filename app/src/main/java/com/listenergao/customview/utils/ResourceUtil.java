package com.listenergao.customview.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;

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

    public static Bitmap getResourceBitmap(Context context, int imgResId) {
        return getResourceBitmap(context, imgResId, -1);
    }

    /**
     * 从资源文件获取Bitmap
     *
     * @param context
     * @param width
     * @return
     */
    public static Bitmap getResourceBitmap(Context context, int imgResId, int width) {
        if (context == null || imgResId < 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只获取宽高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), imgResId, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        if (width > 0) {
            options.inTargetDensity = width;
        } else {
            options.inTargetDensity = options.outWidth;
        }
        Log.d("gys", "outWidth = " + options.outWidth + "   outHeight = " + options.outHeight);
        return BitmapFactory.decodeResource(context.getResources(), imgResId, options);
    }
}
