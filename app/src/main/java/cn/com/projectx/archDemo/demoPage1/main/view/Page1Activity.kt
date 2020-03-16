package cn.com.projectx.archDemo.demoPage1.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.com.projectx.R
import cn.com.projectx.archDemo.demoPage1.blockFunc1.view.BlockFunc1
import cn.com.projectx.archDemo.demoPage1.blockFunc2.view.BlockFunc2
import cn.com.projectx.archDemo.demoPage1.main.viewmodel.MainViewModel
import cn.com.projectx.archDemo.manager.demoFunc1.livedata.GlobalFuncLiveData
import kotlinx.android.synthetic.main.activity_main.*

class Page1Activity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var blockFunc1: BlockFunc1
    lateinit var blockFunc2: BlockFunc2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // 主模块和子模块的初始化顺序是否有依赖？
        // 主模块初始化
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // 这里去解析主参数。主参数由主的ViewModel去存储
        mainViewModel.mainParam = intent?.extras?.getInt("KEY_MAIN_PARAM") ?: 0

        // 对各个子模块进行初始化
        blockFunc1 = BlockFunc1(this,this,clMain, object : BlockFunc1.IProvider {
            override fun getMainData(): MutableLiveData<String> {
                return mainViewModel.mainData
            }

            override fun getBlock2Data(): MutableLiveData<String> {
                // 通过LiveData进行通讯
                return blockFunc2.getBlock2LiveData()
            }
        })

        // block2 可以重点看，这里面讲到了block2如果需要和block1进行数据交互
        blockFunc2 = BlockFunc2(this,this,clMain, object:BlockFunc2.IProvider {
            override fun getBlock1Data(): MutableLiveData<String> {
                // 通过LiveData进行通讯
                return blockFunc1.getBlock1LiveData()
            }

            override fun getMainParam2(): Int {
                // 这里是全部人都需要用到的参数，并且不需要观察他的变化的
                return mainViewModel.mainParam
            }

            override fun getMainData(): MutableLiveData<String> {
                return mainViewModel.mainData;
            }
        })

        // 尽量减少直接在主模块开发功能 ？
        tvMainTitle.setOnClickListener {

            // 主模块功能
            mainViewModel.addMainCount()
        }

        // 对主模块进行初始化
        mainViewModel.mainData.observe(this, Observer {
            // 主模块要调用子模块，举个实际的业务案例？比如是：当前的用户信息
            blockFunc1.callByMainFunc(it)

            blockFunc2.callByMainFunc(it)
        })


        // 上面主要是创建block，创建完Block的LiveData，然后去再去做Block的业务初始化？
        blockFunc1.initBlock()
        blockFunc2.initBlock()

        // 在主模块、子模块都初始化之后，主模块去请求全局数据
        mainViewModel.requestMainData()


        // 对应的模块去观察这个LiveData,跨页面通讯
        GlobalFuncLiveData.get(0).observe(this, Observer {

        })
    }
}
