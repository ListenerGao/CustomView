package com.listenergao.customview.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.transition.TransitionManager;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;

/**
 * create on 19/05/26
 * ConstrainLayout-Placeholder测试
 * Placeholder顾名思义，就是用来一个占位的东西，它可以把自己的内容设置为ConstraintLayout内的其它view。
 * 因此它用来写布局的模版，也可以用来动态修改UI的内容。
 *
 * @author ListenerGao
 */
public class PlaceholderActivity extends BaseActivitty implements View.OnClickListener {

    private ConstraintLayout mRootLayout;
    private Placeholder mPlaceHolder;
    private AppCompatImageView favoriteImage;
    private AppCompatImageView mailImage;
    private AppCompatImageView saveImage;
    private AppCompatImageView playImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder_layout);

        initView();
    }

    private void initView() {

        mRootLayout = findViewById(R.id.root);
        mPlaceHolder = findViewById(R.id.placeholder);
        favoriteImage = findViewById(R.id.favorite);
        mailImage = findViewById(R.id.mail);
        saveImage = findViewById(R.id.save);
        playImage = findViewById(R.id.play);

        favoriteImage.setOnClickListener(this);
        mailImage.setOnClickListener(this);
        saveImage.setOnClickListener(this);
        playImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(mRootLayout);
        mPlaceHolder.setContentId(v.getId());
    }
}
