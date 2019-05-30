package com.listenergao.customview.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.TransitionManager;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;

/**
 * create on 19/05/29
 * ConstraintLayout之ConstraintSet测试
 * ConstraintSet可以用来动态设置约束条件,间距,宽高等属性,
 * 在运用到布局时还可以动态设置动画,实现两个布局的约束互相替换的动画.
 *
 * @author ListenerGao
 */
public class ConstraintSetActivity extends BaseActivitty implements View.OnClickListener {

    private ConstraintLayout mRootLayout;

    boolean isEndLayout = true;
    ConstraintSet constraintSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_set_start_layout);

        mRootLayout = findViewById(R.id.root_layout);
        mRootLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //设置动画
        TransitionManager.beginDelayedTransition(mRootLayout);
        if (constraintSet == null) {
            constraintSet = new ConstraintSet();
        }
        if (isEndLayout = !isEndLayout) {
            mRootLayout.setOnClickListener(this);
            constraintSet.clone(this, R.layout.activity_constraint_set_start_layout);
        } else {
            mRootLayout.setOnClickListener(this);
            //clone 复制整个布局的控件和属性, 接下来做动态切换
            constraintSet.clone(this, R.layout.activity_constraint_set_end_layout);
        }
        constraintSet.applyTo(mRootLayout);
    }
}
