package com.loma

import android.arch.lifecycle.Lifecycle
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import base.BaseActivity

import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.io_main
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast


class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val countries = listOf("Russia", "USA", "England", "Australia")
        selector("Where are you from?", countries) { ds, i ->
            toast("So you're living in ${countries[i]}, right?")
        }


    }


}
