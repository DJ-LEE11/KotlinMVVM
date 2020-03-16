package cn.com.projectx.archDemo.demoPage1.main.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application){

    var mainParam: Int = 0

    // 比如说这里是用户信息，多个模块都要用的
    var mainData = MutableLiveData<String>()

    var mainViewClickCount: Int = 0

    fun addMainCount() {

        mainViewClickCount += 1

        // 请求主数据之后，更新主数据
        mainData.postValue("Main 合计点击次数：$mainViewClickCount")
    }

    fun requestMainData() {

        // 请求主数据之后，更新主数据
        mainData.postValue("Main 合计点击次数：$mainViewClickCount")
    }
}