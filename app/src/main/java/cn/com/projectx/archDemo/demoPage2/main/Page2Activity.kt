package cn.com.projectx.archDemo.demoPage2.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.com.projectx.archDemo.manager.demoFunc1.livedata.GlobalFuncLiveData
import cn.com.projectx.archDemo.manager.demoFunc1.manager.DemoFunGlobalManager

// Page2 只是用来演示如何和其他页面进行跨页面的数据同步、通讯
class Page2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 对应的模块去观察这个LiveData
        DemoFunGlobalManager.globalFuncLiveData.observe(this, Observer {

        })
    }
}