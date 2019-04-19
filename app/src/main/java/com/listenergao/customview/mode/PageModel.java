package com.listenergao.customview.mode;

public class PageModel {
    public int sampleLayoutRes;
    public int titleRes;
    public int practiceLayoutRes;

    public PageModel(int sampleLayoutRes, int titleRes, int practiceLayoutRes) {
        this.sampleLayoutRes = sampleLayoutRes;
        this.titleRes = titleRes;
        this.practiceLayoutRes = practiceLayoutRes;
    }

    public PageModel(int sampleLayoutRes, int titleRes) {
        this.sampleLayoutRes = sampleLayoutRes;
        this.titleRes = titleRes;
    }
}