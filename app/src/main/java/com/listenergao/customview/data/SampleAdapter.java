package com.listenergao.customview.data;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.listenergao.customview.R;
import com.listenergao.customview.mode.SampleMode;

import java.util.List;

public class SampleAdapter extends BaseQuickAdapter<SampleMode, BaseViewHolder> {
    public SampleAdapter(@Nullable List<SampleMode> data) {
        super(R.layout.item_sample_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleMode item) {
        helper.setText(R.id.tv_content, item.getContent());
        helper.setImageResource(R.id.image_view, item.getResId());
    }
}
