package com.listenergao.customview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.R;
import com.listenergao.customview.data.DataResourceUtil;
import com.listenergao.customview.data.SampleAdapter;

/**
 * create on 19/05/24
 * MotionLayout测试
 * MotionLayout 是ConstraintLayout的子类. 作为 连接布局过度和复杂的手势操作之间的桥梁 而生.
 * 类似于属性动画框架, TransitionManager 和 CoordinatorLayout之间的功能集合
 *
 * @author ListenerGao
 */
public class MotionLayoutActivity extends BaseActivitty {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_layout);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initData() {
        SampleAdapter adapter = new SampleAdapter(DataResourceUtil.getSampleData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }


}
