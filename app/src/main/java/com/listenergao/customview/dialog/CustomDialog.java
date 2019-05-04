package com.listenergao.customview.dialog;

import android.content.Context;
import android.os.Bundle;

import com.listenergao.customview.R;

import androidx.appcompat.app.AppCompatDialog;

/**
 * 自定义dialog
 * create on 19/04/30
 *
 * @author ListenerGao
 */
public class CustomDialog extends AppCompatDialog {

    public CustomDialog(Context context) {
        this(context, 0);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
    }
}
