package com.loma.project

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import base.BaseLiActivity
import com.loma.R
import com.loma.project.model.Moudle
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import http.Api
import http.Http
import io.reactivex.rxkotlin.subscribeBy
import org.json.JSONObject
import com.loma.BR

class ProjectDaterailActivity : BaseLiActivity<Moudle>() {
    override fun layoutId() = R.layout.moudle_item

    override fun vm() = com.loma.BR.moudle

    override fun loadMore() {
    }

    override fun onClick(position: Int) {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }


    override fun initData() {
        setLoading()
        Http.returnList<Moudle>(Api.findMoudleByProjectId, JSONObject().put("id", 1)).bindUntilEvent(this, Lifecycle.Event.ON_DESTROY).subscribeBy(onError = { }, onSuccess = {
            adapter.addAll(it)
            if (adapter.itemCount == 0) {
                setEmpty()
            } else {
                setNoTempView()
            }

        })


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_moudle, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.add) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
