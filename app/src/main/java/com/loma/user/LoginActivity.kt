package com.loma.user

import android.os.Bundle
import base.BaseActivity
import com.loma.R
import kotlinx.android.synthetic.main.activity_login.*
import android.databinding.DataBindingUtil
import com.loma.databinding.ActivityLoginBinding
import android.animation.Animator
import android.animation.ObjectAnimator
import android.arch.lifecycle.Lifecycle
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.View
import com.loma.home.HomeActivity
import com.loma.project.model.Project
import com.safframework.log.log
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import db.User
import http.Api
import http.Http
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import xui.toJson
import xui.toast


class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var pre: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pre=   PreferenceManager.getDefaultSharedPreferences(this)
        val mUsername=pre.getString("username","")
        val mPwd=pre.getString("pwd","")
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.user = User().apply { username=mUsername;pwd=mPwd }
        binding.buttonLogin.setOnClickListener { login() }
        binding.buttonCreate.setOnClickListener { regirst() }
        binding.root.setOnSizeChangedListener {
            runOnUiThread {
                if (it) hide()
            }

        }
    }

    fun login() {
        showLoading()
        Http.returnPojo<User>  (Api.APPLogin, binding.user!!)
                .bindUntilEvent(this@LoginActivity, Lifecycle.Event.ON_DESTROY).subscribeBy(onError = {
            toast(it.message)
            closeLoading()

        }, onSuccess = {
            it.toJson().log()
            pre.edit().putString("username",binding!!.user!!.username).apply()
            pre.edit().putString("pwd",binding!!.user!!.pwd).apply()
            closeLoading()
            startActivity<HomeActivity>()
        })


    }

    fun regirst() {

        showLoading()
        var j = JSONObject().put("username", "z").put("pwd", "123456")
        Http.returnList  <Project>  (Api.findAllProject, j)
                .bindUntilEvent(this@LoginActivity, Lifecycle.Event.ON_DESTROY)
                .subscribeBy(onSuccess = {
                    closeLoading();it.toJson().log()
                },onError = {
                    closeLoading();it.toJson().log()
                })



    }


    private fun show() {
        if (binding.mLogo.getVisibility() != View.VISIBLE) {
            mLogo.setVisibility(View.VISIBLE)
            val scaleX = ObjectAnimator.ofFloat(mLogo, "scaleX", 0f, 1f)
            val scaleY = ObjectAnimator.ofFloat(mLogo, "scaleY", 0f, 1f)
            scaleX.setDuration(150).start()
            scaleY.setDuration(150).start()

        }
    }

    private fun hide() {
        if (binding.mLogo.getVisibility() == View.VISIBLE) {
            val scaleX = ObjectAnimator.ofFloat(mLogo, "scaleX", 1f, 0f)
            val scaleY = ObjectAnimator.ofFloat(mLogo, "scaleY", 1f, 0f).apply {

                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {
                        mLogo.setVisibility(View.GONE)

                    }

                    override fun onAnimationCancel(animation: Animator) {

                    }

                    override fun onAnimationRepeat(animation: Animator) {

                    }
                })
            }


            scaleX.setDuration(300).start()
            scaleY.setDuration(300).start()
        }
    }
}
