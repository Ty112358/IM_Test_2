package com.example.im_test.Presenter

import com.example.im_test.Contract.SplashContract

class SlpashPresenter(val view:SplashContract.viewPresenter):SplashContract.checkPresenter {


    override fun checkLogin() {
        if (isLogin()) return view.isOnLogin() else return view.notOnLogin()
    }

    private fun isLogin(): Boolean =false
}