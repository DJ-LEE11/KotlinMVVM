package cn.com.projectx.archDemo.manager.demoFunc1.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

class GlobalFuncLiveData : LiveData<Int>() {

    // 更新数据，这里会同步其他的人
    fun updateValue(data:Int) {
        this.value = data
    }

    // 当外部有人去观察这个数据的时候，可以马上做一些更新之类的操作
    override fun onActive() {
        super.onActive()
    }

    // 当外部没有人观察数据的时候，不需要观察，可以取消一些请求
    override fun onInactive() {
        super.onInactive()
    }


    companion object {
        private lateinit var sInstance: GlobalFuncLiveData

        @MainThread
        fun get(defaultValue:Int): GlobalFuncLiveData {
            sInstance = if (Companion::sInstance.isInitialized) sInstance else GlobalFuncLiveData()
            return sInstance
        }
    }
}