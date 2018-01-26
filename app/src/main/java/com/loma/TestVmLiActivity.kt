package com.loma

import android.os.Bundle
import android.view.View
import android.widget.Button



import base.BaseLiActivity
import xui.toast

class TestVmLiActivity : BaseLiActivity<String>() {

    internal var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        initData();
          adapter.itemClick { i -> toast(i) }
    }

    override fun layoutId(): Int {
        return R.layout.li_item
    }

    override fun vm(): Int {
        return BR.name
    }

    override fun empty(): View {
        val view = layoutInflater.inflate(R.layout.empty, null)
        val button = view.findViewById<Button>(R.id.button).apply {
            setOnClickListener { initData() }

        }

        return view
    }

    override fun loadMore() {
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)

    }

    override fun initData() {
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)
        adapter.add("" + i++)

    }
}
