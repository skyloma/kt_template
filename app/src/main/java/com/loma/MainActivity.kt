package com.loma

import android.Manifest
import android.arch.lifecycle.Lifecycle
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import base.BaseActivity
import com.loma.R.id.text
import com.loma.project.model.Project
import com.safframework.log.*
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
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
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import org.json.JSONObject


class MainActivity : BaseActivity() {


    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { startActivity<Main2Activity>() }

        btLogin.setOnClickListener { save() }
        btPermission.setOnClickListener {
            RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe({ granted ->

                if (granted) {
                    //                                    openCamera()
                    toast("授权${granted}")
                } else {
                    toast("授权${granted}")
                }
            })

        }
//        btDbTest.setOnClickListener {
//            doAsync {
//
//                var u = UserInfo()
//                //
//
//                val box = getApp().db.boxFor<UserInfo>()
//                val j = box.put(u)
//                log(null)
//
//                runOnUiThread {
//                    //更新UI
//                    toast(j)
//                }
//            }
//
//        }
        btLoadProject.setOnClickListener {
            showLoading()
            Http.returnList<Project>(Api.findAllProject, JSONObject().put("id", it)).bindUntilEvent(this@MainActivity, Lifecycle.Event.ON_DESTROY)
        }
        btRxjava.setOnClickListener {
            showLoading()
            var j = JSONObject().put("username", "z").put("pwd", "123456")
//            Http.postJson(Api.APPLogin, j).flatMap {
//                it.Content?.getObject<User>()?.id?.let {
//                    Http.getList<Project>(Api.findAllProject, JSONObject().put("id", it))
//                }
//
//            }.bindUntilEvent(this@MainActivity, Lifecycle.Event.ON_DESTROY)

        }
        btSelect.setOnClickListener {

            val countries = listOf("Russia", "USA", "England", "Australia")
            selector("Where are you from?", countries) { ds, i ->
                toast("So you're living in ${countries[i]}, right?")
            }
        }

    }

    private fun save() {

        showLoading()
        var j = JSONObject().put("username", "z").put("pwd", "123456")
//        Http.getObject< User>(Api.APPLogin, j).bindUntilEvent(this@MainActivity, Lifecycle.Event.ON_DESTROY).subscribeBy(onError = {
//            closeLoading() },
//                onNext = {
//toast(it.username)
//                   it.username?.let {
//                       showDialog(it)
//                   }
//                    val x=   getApp().db.boxFor< User>().put(it)
//                    x.log()
//                }, onComplete = {
//            closeLoading()
//        })
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




