package com.loma


import android.os.Bundle

import base.BaseActivity

import db.*


import ex.getApp

import io.objectbox.kotlin.boxFor

import kotlinx.android.synthetic.main.activity_main2.*
import db.Customer
import db.Order




class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        Flowable.interval(2, 2, TimeUnit.SECONDS)
//                .bindUntilEvent(this@Main2Activity,Lifecycle.Event.ON_STOP)
//                .io_main()
//                .subscribeBy {
//                    it.log()
////                    testview.text=it.toString()
//                }

        save.setOnClickListener { save() }
        find.setOnClickListener{
            var order=Order()
            order.name="aaaaaaaaaaaaaaaaaaaaaaaaaaa"
            order.id=1

            var customer=Customer()
            customer.id=1
            customer.name="cccccccccccccccccccccccccccccc"

            getApp().db.boxFor<Customer>().put(customer)

        }

//        val countries = listOf("Russia", "USA", "England", "Australia")
//        selector("Where are you from?", countries) { ds, i ->
//            toast("So you're living in ${countries[i]}, right?")
//            save()
//        }


    }

    private fun save() {

//        showLoading()
//        val j = JSONObject().put("username", "z").put("pwd", "123456")
//        Http.getObject<User>(Api.APPLogin, j).delay(2,TimeUnit.SECONDS)
//                .subscribeBy(onSuccess = {
//                    closeLoading()
//                    it.log("zjt")
//                    getApp().db.boxFor<User>().put(it)
//                    getApp().db.boxFor<Department>().put(it.department)
//                    getApp().db.boxFor<Role>().put(it.role)
//                }
//                        ,onError = {
//                    closeLoading()
//                    it.log()})

        val studentBox = getApp().db.boxFor(Student::class.java)
        val teacherBox = getApp().db.boxFor(Teacher::class.java)

        // Remove all previous object to have clear start for simplicity's sake
        studentBox.removeAll()
        teacherBox.removeAll()

//        var json="{\"id\":0,\"name\":\"老师1\",\"students\":[{\"id\":1,\"name\":\"学生1\"},{\"id\":2,\"name\":\"学生1\"}]}"
//
//        var t= json.getObject<Teacher>()
//
//          print(t?.name)
//          studentBox.put(t?.students)
//          teacherBox.put(t)

//        var fordbteacher=teacherBox.get(0)
//        print(fordbteacher.toJson())


//
//        studentBox.removeAll()
//        teacherBox.removeAll()

//        student1.teachers.remove(teacher1)
//        student1.teachers.applyChangesToDb() // more efficient than studentBox.put(student1);


    }



}
