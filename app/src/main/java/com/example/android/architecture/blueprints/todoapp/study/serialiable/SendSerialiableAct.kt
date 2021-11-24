package com.example.android.architecture.blueprints.todoapp.study.serialiable

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.android.architecture.blueprints.todoapp.R
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

@Route(path = GroupConstants.Serialiable.sendPath)
class SendSerialiableAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_serialiable_act)
        initClick()
    }

    @SuppressLint("CheckResult")
    fun initClick(){
        RxView.clicks(findViewById(R.id.send_data_btn))
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe {
                    val intent = Intent(this, AcceptSerialiableAct::class.java).apply {
                        putExtra(GroupConstants.Serialiable.param, MySerialiable().apply {
                            name = "张三"
                            age = 18
                        })
                    }
                    startActivity(intent)
//                    ARouter.getInstance().build(GroupConstants.Serialiable.acceptPath)
//                            .withObject(GroupConstants.Serialiable.param,MySerialiable().apply {
//                                name = "张三"
//                                age = 18
//                            })
//                            .navigation()
                }
    }
}