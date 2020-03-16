package cn.com.projectx.archDemo.demoPage1.blockFunc2.view

import android.view.View
import androidx.lifecycle.*
import cn.com.projectx.archDemo.demoPage1.blockFunc2.viewmodel.Block2ViewModel
import kotlinx.android.extensions.LayoutContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import cn.com.projectx.archDemo.base.BaseBlock
import kotlinx.android.synthetic.main.layout_block_func2.*


class BlockFunc2 (storeOwner:ViewModelStoreOwner,lifeOwner: LifecycleOwner, override val containerView: View?, private var mProvider:IProvider) : LayoutContainer,
    BaseBlock(lifeOwner) {

    var mBlock2ViewModel: Block2ViewModel


    // 数据提供者，该模块如果需要监听、获取外部数据
    // 主模块的数据交互，通过provider是因为
    interface IProvider {

        /**
         * 子模块获取主模块的监测数据
         */
        fun getMainData() : MutableLiveData<String>

        /**
         * 获取子模块2的数据监测
         */
        fun getBlock1Data() : MutableLiveData<String>

        /**
         * 子模块获取主模块的参数
         */
        fun getMainParam2() : Int
    }

    init {

        mLifeCycleOwner.lifecycle.addObserver(this)

        // 通过provider观察主模块数据的更新
        // 主模块数据更新的监听
        mProvider.getMainData().observe(mLifeCycleOwner, Observer<String> {

        })

        mBlock2ViewModel = ViewModelProvider(storeOwner).get(Block2ViewModel::class.java)


        // 自行观察子模块的数据变化
        // 这里有些场景比如无需生命周期检测，可以使用 observeForever 实现
        // 举个例子
        mBlock2ViewModel.demoData.observeForever {
            // 一般情况下，该数据由子模块处理
            tvBlock2Title.text = it
        }

        // 当前模块，点击处理
        tvBlock2Title.setOnClickListener {

            // 获取主模块参数，模拟请求参数需要
            val param = mProvider.getMainParam2()

            // 模拟发送请求
            mBlock2ViewModel.requestData(param)

        }
    }




    /////////////////  TODO：抽取一个BASE Block

    fun callByMainFunc(it: String?) {

    }

    fun getBlock2LiveData(): MutableLiveData<String> {
        return mBlock2ViewModel.demoData
    }

    fun initBlock() {

    }

}