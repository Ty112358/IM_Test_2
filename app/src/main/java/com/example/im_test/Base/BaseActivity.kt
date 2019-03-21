package com.example.im_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        init()
    }

    abstract fun setLayout(): Int  //需要一个xml文件返回

    open protected fun init(){

    }  //公共方法




}