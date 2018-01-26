package com.loma

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import base.ActivityStack
import base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import http.Api
import http.Http
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.parallel.ParallelDoOnNextTry


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject
import xui.*


import java.util.ArrayList
import java.util.concurrent.TimeUnit
import android.os.SystemClock
import android.util.Log
import com.google.gson.Gson
import com.socks.library.KLog
import com.trello.lifecycle2.android.lifecycle.RxLifecycleAndroidLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import data.Moudle
import data.Project
import io.reactivex.*
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.subscribeBy


class MainActivity : BaseActivity() {
    lateinit var disposables2Destroy: CompositeDisposable// 管理Destroy取消订阅者者


    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        disposables2Destroy = CompositeDisposable()

        fab.setOnClickListener({

            //     showMsg("ggggggggggggggggggggggggggggg")
//    RxPermissions(this)
//            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            .subscribe({ granted ->
//
//                if (granted) {
////                                    openCamera()
//                    toast("授权${granted}")
//                } else {
//                    toast("授权${granted}")
//                }
//            })

            showLoading()
            var j = JSONObject().put("id", 2)
            Http.postJson(Api.findProjectById, j)
                    .flatMap {
                        it.Content?.getObject<Project>()?.id?.let {
                            Http.getList<Moudle>(Api.findMoudleByProjectId, JSONObject().put("id", it))
                        }


                    }.
                    bindUntilEvent(this@MainActivity,Lifecycle.Event.ON_DESTROY).
                    subscribeBy(onError = { closeLoading() }, onSuccess = { logJson(it) ;closeLoading()} )


        })


//        doAsync {
//
//
//            var jsobj: JSONObject = JSONObject().put("username", "z").put("pwd", "123456")
//            val res = Http.post(Api.APPLogin, jsobj)
//            runOnUiThread {
//                closeLoading()
//                res?.apply {
//                    text.setText(Content.toString())
//                }
//
//            }
//        }

        val list = ArrayList<String>()


    }

    override fun onStop() {
        disposables2Destroy.clear()
        super.onStop()


    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()


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



