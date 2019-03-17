package com.listenergao.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 19/03/10
 *
 * @author listenergao
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<PageModel> mPageModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

    }

    private void initData() {

        mPageModel = new ArrayList<>();

        mPageModel.add(new PageModel(R.layout.sample_color, R.string.title_draw_color));
        mPageModel.add(new PageModel(R.layout.sample_circle, R.string.title_draw_circle));
        mPageModel.add(new PageModel(R.layout.sample_rect, R.string.title_draw_rect));
        mPageModel.add(new PageModel(R.layout.sample_point, R.string.title_draw_point));
        mPageModel.add(new PageModel(R.layout.sample_oval, R.string.title_draw_oval));
        mPageModel.add(new PageModel(R.layout.sample_line, R.string.title_draw_line));
        mPageModel.add(new PageModel(R.layout.sample_round_rect, R.string.title_draw_round_rect));
        mPageModel.add(new PageModel(R.layout.sample_arc, R.string.title_draw_arc));
        mPageModel.add(new PageModel(R.layout.sample_path, R.string.title_draw_path));
        mPageModel.add(new PageModel(R.layout.sample_histogram, R.string.title_draw_histogram));
        mPageModel.add(new PageModel(R.layout.sample_pie_chart, R.string.title_draw_pie_chart));


        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                PageModel pageModel = mPageModel.get(i);
                return PageFragment.newInstance(pageModel.sampleLayoutRes);
            }

            @Override
            public int getCount() {
                return mPageModel.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mPageModel.get(position).titleRes);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class PageModel {
        int sampleLayoutRes;
        int titleRes;
        int practiceLayoutRes;

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


}
