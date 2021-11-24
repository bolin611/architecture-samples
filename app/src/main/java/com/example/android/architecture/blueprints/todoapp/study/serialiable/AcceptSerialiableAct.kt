package com.example.android.architecture.blueprints.todoapp.study.serialiable

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.android.architecture.blueprints.todoapp.R

@Route(path = GroupConstants.Serialiable.acceptPath)
class AcceptSerialiableAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accept_serialiable_act)
        var mySerialiable = intent.getSerializableExtra(GroupConstants.Serialiable.param) as MySerialiable
        var text = "serialiable is null"
        mySerialiable?.let {
            text = "${it.name} ${it.age}"
        }
        val tv = findViewById<TextView>(R.id.accept_data_tv)
        tv.text = text
    }
}