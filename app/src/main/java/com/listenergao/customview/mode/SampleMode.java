package com.listenergao.customview.mode;

import androidx.annotation.DrawableRes;

public class SampleMode {
    @DrawableRes
    private int resId;
    private String content;

    public SampleMode(int resId, String content) {
        this.resId = resId;
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
