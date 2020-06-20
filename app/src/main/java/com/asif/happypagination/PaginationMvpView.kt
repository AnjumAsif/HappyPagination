package com.asif.happypagination

import com.asif.data.response.PaginationResponseX
import com.coherant.asl.ui.base.MvpView

interface PaginationMvpView : MvpView {
    fun isError(error: String?)

    fun isSuccess(mResponse: PaginationResponseX)

    fun loadMoreUser()

    fun isErrorMessage(mErrorTitle: String?, mErrorMessage: String?)

}