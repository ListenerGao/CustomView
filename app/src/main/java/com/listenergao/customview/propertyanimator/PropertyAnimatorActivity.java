package com.listenergao.customview.propertyanimator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;
import com.listenergao.customview.view.SportsWithAnimatorView;

/**
 * @author ListenerGao
 * @date 2019-04-17 10:12
 * <p>
 * 属性动画示例
 * <p>
 * Android的动画分为两类:
 * 一:Animation
 * 其中Animation 又可以再分为View Animation 和 Property Animation两类.
 * View Animation 是纯粹基于 framework 的绘绘制转变,比较简单.
 * Property Animation 属性动画,Android3.0开始引入的新的动画形式,现在项目中的动画99%都是用它,极少用到View Animation
 * 属性动画不仅可以使用自带的API来实现最常用的动画, 而且通过自定义View的凡事来做出定制化的动画.
 * <p>
 * <p>
 * 二:Transition
 */
public class PropertyAnimatorActivity extends BaseActivitty {

    private ImageView mImageView;

    private SportsWithAnimatorView mSportsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);

        initView();
        initAnimator();
    }

    private void initView() {
        mImageView = findViewById(R.id.image_view);
        mSportsView = findViewById(R.id.sports_view);
    }

    private void initAnimator() {
        mImageView.animate()
                .translationX(300)
                .rotation(60)
                //设置动画时长
                .setDuration(2000)
                //设置延迟开始
                .setStartDelay(1000)
                .setListener(imageListener)
                .start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "translationX", 300);
//        animator.setDuration(2000);
//        animator.setStartDelay(1000);
//        animator.start();


        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mSportsView, "progress", 0, 65);
        progressAnimator.setStartDelay(1000);
        //添加动画监听
        progressAnimator.addListener(progressListener);
        //添加动画更新监听
        progressAnimator.addUpdateListener(progressUpdateListener);
        progressAnimator.start();
    }

    private ValueAnimator.AnimatorUpdateListener progressUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int currentValue = (int) animation.getAnimatedValue();
            Log.d("gys", "currentValue = " + currentValue);
        }
    };

    private Animator.AnimatorListener progressListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            Log.d("gys", "progress 动画开始");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("gys", "progress 动画结束");
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.d("gys", "progress 动画取消");
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.d("gys", "progress 动画重复");
        }
    };

    private Animator.AnimatorListener imageListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            Log.d("gys", "image 动画开始");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("gys", "image 动画结束");
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.d("gys", "image 动画取消");
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.d("gys", "image 动画重复");
        }
    };
}
