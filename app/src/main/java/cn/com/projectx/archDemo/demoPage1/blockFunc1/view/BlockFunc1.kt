package cn.com.projectx.archDemo.demoPage1.blockFunc1.view

import android.view.View
import androidx.lifecycle.*
import cn.com.projectx.archDemo.base.BaseBlock
import cn.com.projectx.archDemo.demoPage1.blockFunc1.viewmodel.Block1ViewModel
import kotlinx.android.extensions.LayoutContainer


class BlockFunc1(storeOwner: ViewModelStoreOwner,lifeOwner: LifecycleOwner, override val containerView: View?, private var mProvider: BlockFunc1.IProvider) : LayoutContainer,
    BaseBlock(lifeOwner) {

    interface IProvider
    {

        fun getMainData() : MutableLiveData<String>

        fun getBlock2Data(): MutableLiveData<String>
    }

    var mBlock1ViewModel: Block1ViewModel

    init {

        mBlock1ViewModel = ViewModelProvider(storeOwner).get(Block1ViewModel::class.java)

    }

    fun initBlock() {

        // 这里需要合并 Block 1 和 Block 2 的LiveData，作为合并观察
        // 这里的业务场景如下，比如麦上的被别人关麦 和 自己主动关麦，这里存在多个数据源来改变同一个唯一数据的状态
        val mediatorLiveData = MediatorLiveData<String>()
        mediatorLiveData.addSource(mBlock1ViewModel.demoData)
        { mediatorLiveData.value = "A:$it" }
        mediatorLiveData.addSource(mProvider.getBlock2Data())
        { mediatorLiveData.value = "B:$it" }
        mediatorLiveData.observe(mLifeCycleOwner, Observer<String> {
            // 多源改变UI块状态？
            // update UI

        })
    }

    /**
     * 其他模块如果需要监测或者改变这个模块的数据，可以通过获取这个模块的LiveData来实现，但是需要慎重引入模块的耦合
     */
    fun getBlock1LiveData() : MutableLiveData<String>{
        return mBlock1ViewModel.demoData
    }

    // 主模块如果要同步消息给子模块，需要调用子模块的接口
    fun callByMainFunc(it: String?) {

    }

    // 特定场景需要动态添加数据源
    fun addSource() {

    }

    //
    fun removeSource() {

    }


    override fun onDestroy() {
        super.onDestroy()


    }
}