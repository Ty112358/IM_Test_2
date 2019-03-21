package com.example.im_test.View.Activity

import android.support.v7.widget.Toolbar
import com.example.im_test.Base.BaseActivity
import com.example.im_test.Extra.ToolbarManager
import com.example.im_test.R
import org.jetbrains.anko.find

class SettingActivity:BaseActivity() ,ToolbarManager{

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) } //懒加载

    override fun setLayout(): Int = R.layout.activity_setting

    override fun init() {
        setToolbarTitle("个人中心")
    }
}