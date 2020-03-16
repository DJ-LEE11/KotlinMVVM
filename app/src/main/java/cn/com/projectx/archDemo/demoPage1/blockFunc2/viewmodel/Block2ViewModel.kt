package cn.com.projectx.archDemo.demoPage1.blockFunc2.viewmodel

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class Block2ViewModel(application: Application) : AndroidViewModel(application){

    var demoData = MutableLiveData<String>()

    fun requestData(param:Int) {

        // 模拟请求数据
        Handler().postDelayed({

            // 模拟请求数据成功


        },1000)

    }
    // 释放的时候调用
    override fun onCleared() {
        super.onCleared()

    }
}