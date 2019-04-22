package com.listenergao.customview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.listenergao.customview.R;
import com.listenergao.customview.utils.ResourceUtil;


/**
 * create on 2019/4/20
 *
 * @author ListenerGao
 */
public class MaterialEditText extends androidx.appcompat.widget.AppCompatEditText {
    /**
     * 字体大小
     */
    private static final int TEXT_SIZE = ResourceUtil.dp2px(12);
    /**
     * floating与EditText字之间的间距
     */
    private static final int TEXT_MARGIN = ResourceUtil.dp2px(8);
    /**
     * floating字距离左边的间距
     */
    private static final int HORIZONTAL_OFFSET = ResourceUtil.dp2px(5);
    private static final int VERTICAL_OFFSET = ResourceUtil.dp2px(38);
    private static final int VERTICAL_OFFSET_EXTRA = ResourceUtil.dp2px(16);

    private Paint paint;
    private Rect backgroundPadding = new Rect();

    /**
     * Floating是否在显示
     */
    private boolean floatingLabelShow;
    /**
     * 动画执行的进度
     */
    private float floatingLabelFraction;
    private ObjectAnimator animator;
    /**
     * 是否使用Floating
     */
    private boolean useFloatingLabel = true;


    //    public MaterialEditText(Context context) {
//        this(context, null);
//    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

//    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        Log.d("gys", "3..........");
//        init(context, attrs);
//
//    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        typedArray.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(TEXT_SIZE);
        //使用背景的padding
        getBackground().getPadding(backgroundPadding);
        useFloatingLabel(useFloatingLabel);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (floatingLabelShow && TextUtils.isEmpty(s)) {
                    floatingLabelShow = !floatingLabelShow;
                    //消失动画
                    getObjectAnimator().reverse();

                } else if (!floatingLabelShow && !TextUtils.isEmpty(s)) {
                    floatingLabelShow = !floatingLabelShow;
                    //显示动画
                    getObjectAnimator().start();
                }
            }
        });
    }

    public boolean isUseFloatingLabel() {
        return useFloatingLabel;
    }

    public void setUseFloatingLabel(boolean useFloatingLabel) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel;
            useFloatingLabel(useFloatingLabel);
        }
    }

    private void useFloatingLabel(boolean useFloatingLabel) {
        if (useFloatingLabel) {
            setPadding(backgroundPadding.left, backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN, backgroundPadding.right, backgroundPadding.bottom);
        } else {
            setPadding(backgroundPadding.left, backgroundPadding.top, backgroundPadding.right, backgroundPadding.bottom);
        }
    }

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    private ObjectAnimator getObjectAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0, 1);
        }
        return animator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAlpha((int) (floatingLabelFraction * 0xff));
        canvas.drawText(getHint().toString(), HORIZONTAL_OFFSET,
                VERTICAL_OFFSET - VERTICAL_OFFSET_EXTRA * floatingLabelFraction, paint);
    }
}
