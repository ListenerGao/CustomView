package com.listenergao.customview.activity;

import android.os.Bundle;
import android.view.View;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;
import com.listenergao.customview.dialog.CustomDialog;

import androidx.annotation.Nullable;

/**
 * 示例dialog
 * create on 19/04/30
 *
 * @author ListenerGao
 */
public class SampleDialogActivity extends BaseActivitty implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_dialog);

        findViewById(R.id.btn_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                CustomDialog dialog = new CustomDialog(this);
                dialog.show();
                break;

            default:
                break;
        }
    }
}
