package base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Li
import xui.Pager
import xui.VMAdapter

abstract class BaseLiActivity<T> : AppCompatActivity() {

    lateinit var li: Li
    lateinit var adapter: VMAdapter<T>
    lateinit var pager: Pager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pager = Pager { loadMore() }
        li = Li(this)
        li.addOnScrollListener(pager)
        li.setEmptyView(empty())
        setContentView(li)
        adapter = VMAdapter(vm(), layoutId())
        li.adapter=adapter


    }

    abstract fun layoutId(): Int
    abstract fun vm(): Int
    abstract fun empty(): View
    abstract fun loadMore()
    abstract fun initData()
}
