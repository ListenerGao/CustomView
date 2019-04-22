package com.listenergao.customview.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.listenergao.customview.R;
import com.listenergao.customview.mode.FunctionMode;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author ListenerGao
 * @date 2019-04-19
 */
public class FunctionAdapter extends BaseQuickAdapter<FunctionMode, BaseViewHolder> {

    public FunctionAdapter(@Nullable List<FunctionMode> data) {
        super(R.layout.item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FunctionMode item) {
        helper.setText(R.id.tv_content, item.title);

    }
}
