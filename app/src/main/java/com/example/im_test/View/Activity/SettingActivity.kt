package com.example.im_test.View.Activity

import android.support.v7.widget.Toolbar
import com.example.im_test.Base.BaseActivity
import com.example.im_test.Extra.ToolbarManager
import com.example.im_test.R
import com.example.im_test.Adapter.EMcallbackAdapter
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class SettingActivity:BaseActivity() ,ToolbarManager{

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) } //懒加载
//返回一个设置界面，设置页面没有直接写，而使用了引入的方式；
    override fun setLayout(): Int = R.layout.activity_setting

    override fun init() {
        setToolbarTitle("个人中心")
    }

    override fun initListener() {
        //退出登录监听
        re.setOnClickListener {
            logout()
        }
    }

//logout方法
    private fun logout() {
        EMClient.getInstance().logout(true, object : EMcallbackAdapter() {

            override fun onSuccess() {
                runOnUiThread { //在主线程中执行
                    startActivity<LoginActivity>()
                    EMClient.getInstance().logout(true)
                    toast("退出登录成功")
                }

            }

            override fun onError(code: Int, message: String?) {
                runOnUiThread {
                    toast("退出失败")
                }
            }
        })

    }

}