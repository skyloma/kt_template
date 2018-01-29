package com.loma

import android.arch.lifecycle.Lifecycle
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import base.BaseActivity

import com.socks.library.KLog
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main2.*
import xui.io_main

class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Observable.interval(2, 2, TimeUnit.SECONDS)
                .bindUntilEvent(this@Main2Activity,Lifecycle.Event.ON_DESTROY)
                .io_main()
                .subscribeBy {
                    KLog.d(it)
                    testview.setText(it.toString())
                }




    }


}
