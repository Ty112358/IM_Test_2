package com.example.im_test.View.Activity

import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.example.im_test.Adapter.MessageListAdapter
import com.example.im_test.Base.BaseActivity
import com.example.im_test.Contract.ChattingContract
import com.example.im_test.Presenter.ChattingPresenter
import com.example.im_test.R
import kotlinx.android.synthetic.main.chat_activity.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.item_view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChattingActivity:BaseActivity(),ChattingContract.View {


    override fun setLayout(): Int = R.layout.chat_activity

    val presenter = ChattingPresenter(this)
    lateinit var groupname :String


    override fun init() {
        super.init()
        initHeader()
        initRecycler()
        send.setOnClickListener { send() }
        //enter按键监听
        edit.setOnEditorActionListener{p0,p1,p2 ->
            send()
            true
        }
    }

    private fun initRecycler(){
        recycler_chat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context,presenter.Msgs)
        }
    }

    fun send() {
        hideSoftKeyBoard()
        val msg = edit.text.trim().toString()
        presenter.sendMsg(groupname,msg)
    }

    private fun initHeader() {
        header_back.setOnClickListener {
             finish()
             startActivity<MainActivity>()
             //获取username
     }
        //初始化每一个聊天框标题
        groupname = intent.getStringExtra("groupname")
        val titleString = String.format("群组：%s  聊天中",groupname)
        header_title.text = titleString
    }


//发送消息中、成功、失败
    override fun onStartSendMsg() {
        //刷新列表
        recycler_chat.adapter?.notifyDataSetChanged()

    }

    override fun onSendMsgSuccess() {
        recycler_chat.adapter?.notifyDataSetChanged()
        edit.text.clear()

    }

    override fun onSendMsgFailed() {
        recycler_chat.adapter?.notifyDataSetChanged()
        toast("发送失败")
    }



}