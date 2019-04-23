package com.listenergao.customview.activity;

import android.os.Bundle;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;

import androidx.annotation.Nullable;

/**
 * create on 19/04/23
 *
 * @author ListenerGao
 */
public class TagLayoutActivitty extends BaseActivitty {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
    }
}
