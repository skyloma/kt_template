package xui;

import android.view.View;

/**
 * Created by cundong on 2015/10/9.
 * RecyclerView/List/GridView 滑动加载下一页时的回调接口
 */
public interface OnListLoadNextPageListener {

    /**
     * 开始加载下一页
     *
     * @param view 当前RecyclerView/List/GridView
     */
    public void onLoadNextPage(View view);
}