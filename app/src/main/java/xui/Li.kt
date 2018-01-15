package android.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class Li : android.support.v7.widget.RecyclerView {
    private var emptyView: View? = null
    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context) {
        layoutManager = LinearLayoutManager(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        layoutManager = LinearLayoutManager(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        layoutManager = LinearLayoutManager(context)

    }


    internal fun checkIfEmpty() {

        emptyView?.let {
            if (adapter != null) {

                if (adapter.itemCount == 0) {
                    (rootView as ViewGroup).addView(emptyView)
                } else {
                    (rootView as ViewGroup).removeView(emptyView)
                }


            }
        }


    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?){
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)

        checkIfEmpty()

    }

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView

    }
}