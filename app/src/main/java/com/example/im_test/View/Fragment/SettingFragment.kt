package com.example.im_test.View.Fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.im_test.R
import com.example.im_test.View.Activity.LoginActivity
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.startActivity

class SettingFragment:PreferenceFragment() {

//返回的个人中心页面
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {


        return super.onPreferenceTreeClick(preferenceScreen, preference)

    }


}