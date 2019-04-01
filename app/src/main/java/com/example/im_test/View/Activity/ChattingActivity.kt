package com.example.im_test.View.Activity

import com.example.im_test.Base.BaseActivity
import com.example.im_test.R
import kotlinx.android.synthetic.main.chat_activity.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.item_view.*
import org.jetbrains.anko.startActivity

class ChattingActivity:BaseActivity() {
    override fun setLayout(): Int {
        return R.layout.chat_activity
    }

    override fun init() {
        super.init()
        initHeader()
    }

    private fun initHeader() {
     header_back.setOnClickListener {
         finish()
         startActivity<MainActivity>()

         //获取username

     }
        //初始化每一个聊天框标题
        var groupname = intent.getStringExtra("groupname")
        val titleString = String.format("群组：%s  聊天中",groupname)
        header_title.text = titleString
    }


}