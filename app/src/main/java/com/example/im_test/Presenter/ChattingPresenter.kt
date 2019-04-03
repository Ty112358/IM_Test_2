package com.example.im_test.Presenter

import android.os.Handler
import android.os.Looper
import com.example.im_test.Adapter.EMcallbackAdapter
import com.example.im_test.Contract.ChattingContract
import com.example.im_test.Presenter.LoginPresenter.Companion.handler
import com.hyphenate.chat.EMMessage
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient


class ChattingPresenter(val view:ChattingContract.View):ChattingContract.Presenter {

//主线程调用
    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    private fun uiThread(f:() -> Unit){
        LoginPresenter.handler.post { f() }
    }

    //创建一个可改列表，管理消息
    val Msgs = mutableListOf<EMMessage>()

    //发送消息
    override fun sendMsg(groupName: String, message: String) {
        //创建一条文本消息
        val emMsg = EMMessage.createTxtSendMessage(message, groupName)
        //监听消息状态
        emMsg.setMessageStatusCallback(object : EMcallbackAdapter() {

            override fun onSuccess() {
                uiThread { view.onSendMsgSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMsgFailed() }
            }

        })

        Msgs.add(emMsg) //加入列表
        view.onStartSendMsg() //开始
        EMClient.getInstance().chatManager().sendMessage(emMsg) //发送

    }
}