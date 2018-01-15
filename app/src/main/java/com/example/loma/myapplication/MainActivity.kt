package com.example.loma.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import xui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:  RecyclerViewAdapter<String>
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            adapter.add("${i++}")
        }


//        list_view.setEmptyView(getLayoutInflater().inflate(R.layout.empty, null))
        adapter = object : RecyclerViewAdapter<String>(android.R.layout.simple_list_item_1) {
            override fun onBind(holder: BaseViewHolder, item: String, position: Int) {

                holder.setText(android.R.id.text1, item)


            }


        }


        adapter.itemClickDoing {  p -> adapter.remove(p) }


        doAsync {
            log(Thread.currentThread().id)

            runOnUiThread {

                log(Thread.currentThread().id)
            }
        }

        val list = ArrayList<String>()

        adapter.data = list
        list_view.adapter = adapter
//        val recyclerOnScrollListener = object : RecyclerOnScrollListener() {
//          override  fun onLoadNextPage(view: View) {
//                super.onLoadNextPage(view)
//                toast("ddddddddddddddddddddddddd")
//              adapter.add("${i++}")
//            }
//        }
//
//
//        list_view.addOnScrollListener(recyclerOnScrollListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {

            return true
        }

        return super.onOptionsItemSelected(item)
    }


}
