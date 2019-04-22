package com.listenergao.customview;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.listenergao.customview.adapter.CustomViewFunctionAdapter;
import com.listenergao.customview.data.DataResourceUtil;
import com.listenergao.customview.mode.FunctionMode;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * create on 19/03/10
 *
 * @author listenergao
 */
public class MainActivity extends BaseActivitty implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initData() {

        List<FunctionMode> functionListData = DataResourceUtil.getFunctionListData(getResources());
        CustomViewFunctionAdapter adapter = new CustomViewFunctionAdapter(functionListData);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FunctionMode item = (FunctionMode) adapter.getItem(position);
        if (item != null && item.clz != null) {
            startActivity(item.clz);
        }
    }
}
