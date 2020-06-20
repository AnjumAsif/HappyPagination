package com.asif.happypagination

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.asif.data.RetrofitService
import com.asif.data.response.PaginationResponseX
import com.asif.happypagination.PaginationMvpView
import com.asif.utility.Utils
import com.coherant.asl.ui.base.BasePresenter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class PaginationPresenter @Inject internal constructor() : BasePresenter<PaginationMvpView>() {
    private var mActivitys: Activity? = null
    override fun attachView(
        mvpView: PaginationMvpView?,
        mActivity: AppCompatActivity?
    ) {
        mActivitys = mActivity
        super.attachView(mvpView, mActivity)
    }

    fun callApiForGetData(apiKey: String, currentPage: Int) {
        RetrofitService.getInstance(mActivitys!!).getApi()
            .getPaginationData(apiKey, currentPage.toLong())
            .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<PaginationResponseX> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: PaginationResponseX) {
                        getMvpView().isSuccess(t)
                    }

                    override fun onError(e: Throwable) {

                        e.printStackTrace()
                    }
                })

    }
}