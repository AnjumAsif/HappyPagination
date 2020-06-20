package com.coherant.asl.ui.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.asif.base.Presenter

open class BasePresenter<T : MvpView> : Presenter<T> {
    private var mMvpView: T? = null
    override fun attachView(mvpView: T?) {
        mMvpView = mvpView
    }

    override fun attachView(mvpView: T?, mActivity: Activity?) {
        mMvpView = mvpView
    }

    override fun attachView(mvpView: T?, mActivity: AppCompatActivity?) {
        mMvpView = mvpView
    }

    override fun detachView() {
        mMvpView = null

    }

    private fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): T {
        return mMvpView!!
    }

    private class MvpViewNotAttachedException private constructor() : RuntimeException(
        "Please call Presenter.attachView(MvpView) before" +
                " requesting data to the Presenter"
    )
}