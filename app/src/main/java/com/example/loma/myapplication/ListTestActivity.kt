package com.example.loma.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_list_test.*
import xui.*

class ListTestActivity : AppCompatActivity() {

    lateinit var adapter: DataAdapter<String>
    internal var i = 0
    lateinit var pager: Pager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_test)
        adapter =   DataAdapter<String>( android.R.layout.simple_list_item_1,
         {baseViewHolder, s, i ->
            baseViewHolder.setText(android.R.id.text1, s)
        }
        )

        adapter.itemClick { i -> toast(i) }
        pager = Pager {  loadMore()  }
        li.addOnScrollListener(pager )
        li.setEmptyView(layoutInflater.inflate(R.layout.empty,null))
        li.adapter=adapter
    }

    private fun loadMore() {
        Log.e("zjt","onScrollStateChanged"+i)
        adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce"); adapter.add("${i++} abce");

        if (adapter.itemCount > 50) {
            pager.setHasMore(false)
        }

    }

    fun add(view: View) {
        adapter.add(i++.toString() + "")
    }
}
