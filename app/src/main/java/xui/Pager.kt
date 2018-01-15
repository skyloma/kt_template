package xui

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View

/**
 * Created by cundong on 2015/10/9.
 *
 *
 * 继承自RecyclerView.OnScrollListener，可以监听到是否滑动到页面最低部
 */
class Pager(  var next: (() -> Unit) ) : RecyclerView.OnScrollListener() {
    /**
     * 当前RecyclerView类型
     */
    protected var layoutManagerType: LayoutManagerType? = null

    /**
     * 最后一个的位置
     */
    private var lastPositions: IntArray? = null

    /**
     * 最后一个可见的item的位置
     */
    private var lastVisibleItemPosition: Int = 0

    /**
     * 当前滑动的状态
     */
    private var currentScrollState = 0
    private var isHasMore = true



    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView!!.layoutManager

        if (layoutManagerType == null) {
            if (layoutManager is LinearLayoutManager) {
                layoutManagerType = LayoutManagerType.LinearLayout
            } else if (layoutManager is GridLayoutManager) {
                layoutManagerType = LayoutManagerType.GridLayout
            } else if (layoutManager is StaggeredGridLayoutManager) {
                layoutManagerType = LayoutManagerType.StaggeredGridLayout
            } else {
                throw RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager")
            }
        }

        when (layoutManagerType) {
            Pager.LayoutManagerType.LinearLayout -> lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            Pager.LayoutManagerType.GridLayout -> lastVisibleItemPosition = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            Pager.LayoutManagerType.StaggeredGridLayout -> {
                val staggeredGridLayoutManager = layoutManager as StaggeredGridLayoutManager
                if (lastPositions == null) {
                    lastPositions = IntArray(staggeredGridLayoutManager.spanCount)
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions)
                lastVisibleItemPosition = findMax(lastPositions!!)
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        currentScrollState = newState
        val layoutManager = recyclerView!!.layoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount


        if (totalItemCount - lastVisibleItemPosition <= ITEM_LEFT_TO_LOAD_MORE || totalItemCount - lastVisibleItemPosition == 0 && totalItemCount > visibleItemCount) {
            if (isHasMore && !recyclerView.canScrollVertically(1)) {

                next.invoke()
            }


        }
    }

    fun setHasMore(hasMore: Boolean) {
        this.isHasMore = hasMore
    }

    /**
     * 取数组中最大值
     *
     * @param lastPositions
     * @return
     */
    private fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }

        return max
    }


    enum class LayoutManagerType {
        LinearLayout, StaggeredGridLayout, GridLayout
    }



    val ITEM_LEFT_TO_LOAD_MORE = 10

}