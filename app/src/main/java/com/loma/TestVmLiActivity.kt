package com.loma

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.view.View
import android.widget.Button


import base.BaseLiActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.loma.project.model.Project
import com.safframework.log.log
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import http.Api
import http.Http
import io.reactivex.rxkotlin.subscribeBy
import org.json.JSONObject
import xui.getList
import xui.toJson
import xui.toast

class TestVmLiActivity : BaseLiActivity<Project>() {
    override fun onClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    internal var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.itemClick { i -> toast(i) }


    }



    override fun layoutId(): Int {
        return R.layout.project_item
    }

    override fun vm(): Int {
        return BR.project
    }


    override fun loadMore() {


    }

    override fun initData() {
        adapter.add(Project().apply { name = "dfsdfasdfdsfds" })
        adapter.add(Project().apply { name = "dfsdfasdfdsfds" })
        adapter.add(Project().apply { name = "dfsdfasdfdsfds" })
        adapter.add(Project().apply { name = "dfsdfasdfdsfds" })

        Http.returnList<Project>(Api.findAllProject, JSONObject()).bindUntilEvent(this, Lifecycle.Event.ON_DESTROY).subscribeBy(onError = { toast(it.localizedMessage) }, onSuccess = {

            adapter.addAll(it)

        })

    }
}
