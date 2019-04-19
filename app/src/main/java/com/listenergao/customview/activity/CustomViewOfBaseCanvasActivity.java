package com.listenergao.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.listenergao.customview.BaseActivitty;
import com.listenergao.customview.PageFragment;
import com.listenergao.customview.R;
import com.listenergao.customview.data.DataResourceUtil;
import com.listenergao.customview.mode.PageModel;

import java.util.List;

/**
 * @author ListenerGao
 * @date 2019-04-19
 * <p>
 * 自定义View1-1绘制基础
 */
public class CustomViewOfBaseCanvasActivity extends BaseActivitty {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_of_base_canvas);

        initView();
        initData();

    }

    private void initView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

    }

    private void initData() {

        final List<PageModel> pageModes = DataResourceUtil.getPageModelData();

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                PageModel pageModel = pageModes.get(i);
                return PageFragment.newInstance(pageModel.sampleLayoutRes);
            }

            @Override
            public int getCount() {
                return pageModes.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModes.get(position).titleRes);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}

