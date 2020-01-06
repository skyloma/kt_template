package com.loma.project

import android.arch.lifecycle.Lifecycle
import base.BaseLiFragment
import com.loma.BR
import com.loma.R
import com.loma.project.model.Project
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import http.Api
import http.Http
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.toast
import org.json.JSONObject


class ProjectListFragment : BaseLiFragment<Project>() {


    override fun layoutId() = R.layout.project_item
    override fun vm(): Int = BR.project
    override fun loadMore() {}
    override fun initData() {
         setLoading()
        Http.returnList<Project>(Api.findAllProject, JSONObject()).bindUntilEvent(this, Lifecycle.Event.ON_DESTROY).subscribeBy(onError = { closeProgress();activity?.toast(it.localizedMessage) }, onSuccess = {
            adapter.addAll(it)
            if (adapter.itemCount==0){
                setEmpty()
            }else{setNoTempView()}



        })


    }

    override fun onClick(position: Int) {
        activity!!.toast("$position")

    }


}
