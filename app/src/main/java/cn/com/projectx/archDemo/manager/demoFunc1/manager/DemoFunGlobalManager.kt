package cn.com.projectx.archDemo.manager.demoFunc1.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


object DemoFunGlobalManager {

    val globalFuncLiveData: MutableLiveData<Int> = MutableLiveData<Int>()


    fun updateDemoData(test:Int) {
        globalFuncLiveData.value = (test)
    }

    /**
     * 这里是从网络请求，考虑repository模式
     */
    fun refreshDemoDataByInternet() {

    }

}