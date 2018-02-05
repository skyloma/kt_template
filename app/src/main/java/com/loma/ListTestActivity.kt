package com.loma

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import db.UserInfo
import kotlinx.android.synthetic.main.activity_list_test.*
import xui.*

class ListTestActivity : AppCompatActivity() {

    lateinit var dataAdapter: DataAdapter<UserInfo>
    internal var i = 0
    lateinit var pager: Pager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_test)

        li.apply {
            pager = Pager { loadMore() }
            addOnScrollListener(pager)
            setEmptyView( inflate(R.layout.empty ))
            dataAdapter = DataAdapter<UserInfo>(android.R.layout.simple_list_item_1, { h, s, i ->
                s.apply {
                    //绑定数据
                    h.setText(android.R.id.text1, h.toString())
                }

            }).apply {
                itemClick { i ->
                    //处理点击
                }
            }
            adapter = dataAdapter

        }


    }

    private fun loadMore() {
        Log.e("zjt", "onScrollStateChanged" + i)
        UserInfo(0).let {

            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)
            dataAdapter.add(it)

        }

            if (dataAdapter.itemCount > 50) {
                pager.setHasMore(false)
            }

        }

        fun add(view: View) {
            UserInfo(0)?.let {

                dataAdapter.add(it)


            }

        }
    }
