package com.listenergao.customview.propertyanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;
import com.listenergao.customview.view.CameraView;

/**
 * create on 2019/4/20
 *
 * @author ListenerGao
 */
public class ObjectAnimatorActivity extends BaseActivitty {

    private CameraView mCameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        initView();
        initAnimator();

    }

    private void initView() {
        mCameraView = findViewById(R.id.camera_view);
    }

    private void initAnimator() {
        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(mCameraView, "bottomFlip", 30);
        bottomFlipAnimator.setDuration(1000);

        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(mCameraView, "topFlip", -30);
        topFlipAnimator.setDuration(1000);
        topFlipAnimator.setStartDelay(300);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(mCameraView, "flipRotation", 270);
        flipRotationAnimator.setDuration(1000);
        flipRotationAnimator.setStartDelay(300);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();

        //也可以使用下面这种方法
        /*PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 30);
        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -30);
        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mCameraView, bottomFlipHolder, flipRotationHolder, topFlipHolder);
        animator.setDuration(1000);
        animator.setDuration(3000);
        animator.start();*/
    }
}
