package com.listenergao.customview.activity;

import android.os.Bundle;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;

import androidx.annotation.Nullable;

/**
 * create on 19/05/15
 * ConstrainLayout测试
 *
 * @author ListenerGao
 */
public class ConstrainLayoutActivity extends BaseActivitty {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrain_layout);
    }
}
