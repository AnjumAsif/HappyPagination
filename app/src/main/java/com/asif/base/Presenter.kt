package com.asif.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.coherant.asl.ui.base.MvpView

interface Presenter<V : MvpView> {
    fun attachView(mvpView: V?)
    fun attachView(mvpView: V?, mActivity: Activity?)
    fun attachView(mvpView: V?, mActivity: AppCompatActivity?)
    fun detachView()

}