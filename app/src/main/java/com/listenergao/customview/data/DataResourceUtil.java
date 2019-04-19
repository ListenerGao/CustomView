package com.listenergao.customview.data;

import android.content.res.Resources;

import com.listenergao.customview.R;
import com.listenergao.customview.activity.CustomViewActivity;
import com.listenergao.customview.activity.CustomViewOfBaseCanvasActivity;
import com.listenergao.customview.mode.FunctionMode;
import com.listenergao.customview.mode.PageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据工具类
 *
 * @author ListenerGao
 * @date 2019-04-19
 */
public class DataResourceUtil {


    public static List<PageModel> getPageModelData() {
        List<PageModel> pageModels = new ArrayList<>();

        pageModels.add(new PageModel(R.layout.sample_color, R.string.title_draw_color));
        pageModels.add(new PageModel(R.layout.sample_circle, R.string.title_draw_circle));
        pageModels.add(new PageModel(R.layout.sample_rect, R.string.title_draw_rect));
        pageModels.add(new PageModel(R.layout.sample_point, R.string.title_draw_point));
        pageModels.add(new PageModel(R.layout.sample_oval, R.string.title_draw_oval));
        pageModels.add(new PageModel(R.layout.sample_line, R.string.title_draw_line));
        pageModels.add(new PageModel(R.layout.sample_round_rect, R.string.title_draw_round_rect));
        pageModels.add(new PageModel(R.layout.sample_arc, R.string.title_draw_arc));
        pageModels.add(new PageModel(R.layout.sample_path, R.string.title_draw_path));
        pageModels.add(new PageModel(R.layout.sample_histogram, R.string.title_draw_histogram));
        pageModels.add(new PageModel(R.layout.sample_pie_chart, R.string.title_draw_pie_chart));

        return pageModels;
    }

    public static List<FunctionMode> getFunctionListData(Resources resources) {
        List<FunctionMode> functionModes = new ArrayList<>();

        functionModes.add(new FunctionMode(resources.getString(R.string.custom_view), CustomViewActivity.class));

        return functionModes;
    }

    public static List<FunctionMode> getCustomFunctionListData(Resources resources) {
        List<FunctionMode> functionModes = new ArrayList<>();

        functionModes.add(new FunctionMode(resources.getString(R.string.custom_view_1), CustomViewOfBaseCanvasActivity.class));
        functionModes.add(new FunctionMode(resources.getString(R.string.custom_view_2), CustomViewOfBaseCanvasActivity.class));
        functionModes.add(new FunctionMode(resources.getString(R.string.custom_view_3), CustomViewOfBaseCanvasActivity.class));

        return functionModes;
    }
}
