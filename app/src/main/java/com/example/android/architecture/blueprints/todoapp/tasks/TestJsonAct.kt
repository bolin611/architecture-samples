package com.example.android.architecture.blueprints.todoapp.tasks

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.android.architecture.blueprints.todoapp.R
import com.google.gson.Gson

@Route(path = "/test/testjson")
class TestJsonAct : AppCompatActivity(){

    @Autowired
    @JvmField var testParam:String? = null

    val TAG = "testjson"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.tasks_act)
    }

    override fun onResume() {
        super.onResume()
//        testBundleGson()
    }

    fun testBundleGson(){
        intent.putExtra("intent_data",intent.extras.toString())
        val gson = Gson()
        val bundleStr = gson.toJson(intent.extras)
        Log.d(TAG,"bundleStr = $bundleStr")
    }
}