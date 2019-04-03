package com.example.im_test.Contract

import com.example.im_test.Base.BaseContract

interface ChattingContract {

    interface Presenter:BaseContract{
        fun sendMsg(userName:String,message : String)
    }

    interface View{

        fun onStartSendMsg()
        fun onSendMsgSuccess()
        fun onSendMsgFailed()
    }
}