package com.listenergao.customview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基类
 *
 * @author ListenerGao
 * @date 2019-04-19
 */
public class BaseActivitty extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void startActivity(@NonNull final Class<? extends Activity> clz) {
        startActivity(null, clz);
    }

    public void startActivity(Bundle extras, @NonNull final Class<? extends Activity> clz) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.setComponent(new ComponentName(this.getPackageName(), clz.getName()));
        startActivity(intent);
    }
}
