package com.listenergao.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;

/**
 * 自定义圆形带border头像
 * create on 19/04/08
 *
 * @author ListenerGao
 */
public class AvatarView extends View {
    /**
     * 头像大小
     */
    private static final int WIDTH = ResourceUtil.dp2px(300);
    /**
     * 间距
     */
    private static final int PADDING = ResourceUtil.dp2px(30);
    /**
     * border宽度
     */
    private static final int BORDER_WIDTH = ResourceUtil.dp2px(4);
    Bitmap avatar;
    Paint paint;
    RectF rectF;
    Xfermode xfermode;
    RectF border;
    private Context mContext;

    public AvatarView(Context context) {
        this(context, null);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //ANTI_ALIAS_FLAG 抗锯齿模式, 也可以使用paint.setAntiAlias()设置, 默认是关闭的
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        avatar = ResourceUtil.getResourceBitmap(mContext, R.drawable.sample, WIDTH);
        rectF = new RectF();
        border = new RectF();
        //"Xfermode"其实就是"Transfer mode", 设置绘制内容和View中已有内容的混合计算方式
        //使用Xfermode绘制内用时,注意要使用离屏缓冲,同时应注意
        //它的透明区域不要太小, 要让它足够覆盖到要和它集合绘制的内容,否则结果可能不是你想要的
        //本示例中, 选择绘制的图片过小时, 绘制后头像就不能完全显示
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
        border.set(PADDING - BORDER_WIDTH, PADDING - BORDER_WIDTH, PADDING + WIDTH + BORDER_WIDTH, PADDING + WIDTH + BORDER_WIDTH);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画border
        canvas.drawOval(border, paint);
        //saveLayer可以为Canvas创建一个新的图层
        //Canvas.saveLayer做短时的离屏缓冲, 将要绘制的内容单独绘制在缓冲层.(注意:绘制前保存,绘制后恢复)
        int saved = canvas.saveLayer(rectF, paint);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(xfermode);
        //画头像
        canvas.drawBitmap(avatar, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
    }
}
