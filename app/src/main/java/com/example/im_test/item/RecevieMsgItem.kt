package com.example.im_test.item

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im_test.R

class RecevieMsgItem(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
            View.inflate(context, R.layout.msg_recevie_item,this)
    }

}