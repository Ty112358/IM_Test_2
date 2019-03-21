package com.example.im_test.View.Activity

import android.support.v7.widget.Toolbar
import com.example.im_test.Base.BaseActivity
import com.example.im_test.Extra.ToolbarManager
import com.example.im_test.R
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolbarManager {

//重载toolbar
    override val toolbar:Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

//返回xml文件层，toolbar包括两层，一层view，一层modle；；在xml中写了view
    override fun setLayout(): Int = R.layout.activity_main


//在初始化中，加载toolbar的设置、点击事件
    override fun init() {
        initMainToolbar()
    }


}
