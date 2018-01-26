package com.loma

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.loma.R
import data.Project
import kotlinx.android.synthetic.main.activity_list_test.*
import xui.*
import java.util.*

class ListTestActivity : AppCompatActivity() {

    lateinit var adapter: DataAdapter<Project>
    internal var i = 0
    lateinit var pager: Pager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_test)
        adapter = DataAdapter<Project>(android.R.layout.simple_list_item_1, { h, s, i ->

            s.apply {
                h.setText(android.R.id.text1, id.toString())
                h.setText(android.R.id.text1, name)
                h.setText(android.R.id.text1, date?.getString10())

            }

        }).apply {
            itemClick { i -> toast(i) }
        }


        pager = Pager { loadMore() }
        li.addOnScrollListener(pager)
        li.setEmptyView(layoutInflater.inflate(R.layout.empty, null))
        li.adapter = adapter
    }

    private fun loadMore() {
        Log.e("zjt", "onScrollStateChanged" + i)
        Project().let {
            it.date = Date()
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)
            adapter.add(it)

        }

        if (adapter.itemCount > 50) {
            pager.setHasMore(false)
        }

    }

    fun add(view: View) {
        Project().let {
            it.date = Date()
            adapter.add(it)


        }

    }
}
