package com.example.im_test.View.Activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.im_test.Adapter.MessageListAdapter
import com.example.im_test.Adapter.MsgListenerAdapter
import com.example.im_test.Base.BaseActivity
import com.example.im_test.Contract.ChattingContract
import com.example.im_test.Presenter.ChattingPresenter
import com.example.im_test.R
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.chat_activity.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChattingActivity:BaseActivity(),ChattingContract.View {


    override fun setLayout(): Int = R.layout.chat_activity

    val presenter = ChattingPresenter(this)
    lateinit var groupname :String

//注册监听器
    val msgListener = object : MsgListenerAdapter() {

    override fun onMessageReceived(p0: MutableList<EMMessage>?) {
        presenter.addMsg(groupname,p0)
        //view层
        runOnUiThread {recycler_chat.adapter?.notifyDataSetChanged() }
    }
}


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
        //启用监听
        EMClient.getInstance().chatManager().addMessageListener(msgListener)
        //初始化聊天记录
        presenter.loadMsgs(groupname)


    }

    private fun initRecycler(){
        recycler_chat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context,presenter.Msgs)

            //检测空闲状态，是否到顶，加载更多数据
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE){
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0){
                            presenter.loadMoreMsgs(groupname)
                        }
                    }
                }

            })
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
        ToBottom()
    }


    override fun onSendMsgFailed() {
        recycler_chat.adapter?.notifyDataSetChanged()
        toast("发送失败")
    }

   /* override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(msgListener)
    }

*/
    //滚动到底部
   private fun ToBottom() {
       recycler_chat.scrollToPosition(presenter.Msgs.size - 1)
   }
//初始化加载消息，在view层的实现
    override fun onLoadMsgs() {
        recycler_chat.adapter?.notifyDataSetChanged()
        ToBottom()
    }
//加载更多数据
    override fun onMoreMsgsLoad(size: Int) {
        recycler_chat.adapter?.notifyDataSetChanged()
        recycler_chat.scrollToPosition(size)
    }
}