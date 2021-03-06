package com.example.im_test.Base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.inputMethodManager
import org.jetbrains.anko.progressDialog

abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        init()
        initListener()
    }


    open fun initListener() {

    } //初始化监听


    abstract fun setLayout(): Int  //需要一个xml文件返回

    open fun init(){

    }  //公共方法

//进度条
    val progress by lazy {

        ProgressDialog(this)

    } //懒加载，context暂时设置为this


    fun showProgress(msg:String){

        progress.setMessage(msg)
        progress.show()

    }//显示进度

    fun dissmissProgress(){
        progress.dismiss()
    }//结束进度

//隐藏软键盘
    val inputMethod by lazy {
    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}

    fun hideSoftKeyBoard(){
        inputMethod.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }


}