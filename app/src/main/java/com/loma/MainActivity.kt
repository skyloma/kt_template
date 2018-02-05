package com.loma

import android.Manifest
import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import base.BaseActivity
import com.loma.R.id.text
import com.safframework.log.*
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import db.Project
import db.User
import io.reactivex.disposables.CompositeDisposable

import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_main.*


import java.util.ArrayList

import db.UserInfo

import ex.getApp
import http.Api
import http.Http
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.io_main
import io.reactivex.rxkotlin.Flowables
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import xui.getObject
import xui.toast
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity() {
    lateinit var disposables2Destroy: CompositeDisposable// 管理Destroy取消订阅者者


    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        disposables2Destroy = CompositeDisposable()

        Flowable.interval(2, 2, TimeUnit.SECONDS)
                .bindUntilEvent(this@MainActivity,Lifecycle.Event.ON_DESTROY)
                .io_main()
                .subscribeBy {
                             it.log()
                  text.text=it.toString()
                }


        fab.setOnClickListener({

            //     showMsg("ggggggggggggggggggggggggggggg")
//          var intent1= Intent(this@MainActivity,ListTestActivity::class.java)
//            startActivity(intent1)
            startActivity<ListTestActivity>()

            RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe({ granted ->

                if (granted) {
                    //                                    openCamera()
                    toast("授权${granted}")
                } else {
                    toast("授权${granted}")
                }
            })

            showLoading()
            var j = JSONObject().put("username", "z").put("pwd","123456")
            Http.postJson(Api.APPLogin, j)
                    .flatMap {
                        it.Content?.getObject<User>()?.id?.let {
                            Http.getList<Project>(Api.findAllProject, JSONObject().put("id", it))
                        }

                    }
                    .bindUntilEvent(this@MainActivity,Lifecycle.Event.ON_DESTROY)
                    .subscribeBy(onError = { closeLoading() }, onNext = {it.log()} ,onComplete = {
                       closeLoading()
                    })
//                 fab.visibility=View.GONE
            doAsync {

                var u = UserInfo()
                //

                val box = getApp().db.boxFor<UserInfo>()
                box.put(u)
                log(null)

                runOnUiThread {
                    //更新UI

                }
            }


//                box.put(u).log()
        })



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




