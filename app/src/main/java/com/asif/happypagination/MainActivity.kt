package com.asif.happypagination

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asif.base.BaseActivity
import com.asif.data.response.PaginationResponseX
import com.asif.data.response.Results
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(),PaginationMvpView {
    val apiKey = "af39e67d54a91fab9c35b42cd8f774de"
    companion object {
        private const val PAGE_START = 1
    }
    private var isLoading = false
    private var isLastPage = false
    private var TOTAL_PAGES: Int = 100000
    private var currentPage: Int = PAGE_START
    @Inject
    lateinit var mPresenter: PaginationPresenter
    lateinit var mMessageAdapter: PaginationAdapter
    private var mFeedList: ArrayList<Results>? = null
    private var mResponse: PaginationResponseX? = null
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()?.inject(this)
        setContentView(R.layout.activity_main)
        mPresenter.attachView(this, this)
        mPresenter.getMvpView().setUp()



    }

    override fun isError(error: String?) {
        hideLoading()
    }

    override fun isSuccess(mResponse: PaginationResponseX) {
        hideLoading()
        this.mResponse = mResponse
        mFeedList!!.addAll(mResponse.results)
        mMessageAdapter.notifyDataSetChanged()
    }

    override fun loadMoreUser() {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        showLoading()
        mPresenter.callApiForGetData(apiKey, currentPage)
    }

    override fun isErrorMessage(mErrorTitle: String?, mErrorMessage: String?) {
        hideLoading()
    }

    override fun setUp() {
        mFeedList = ArrayList()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mMessageAdapter = PaginationAdapter()
        mMessageAdapter.setList(this, mFeedList!!)
        recyclerPagination.layoutManager = layoutManager
        recyclerPagination.adapter = mMessageAdapter

        recyclerPagination.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (currentPage <= TOTAL_PAGES) {
                    if (visibleItemCount?.plus(firstVisibleItemPosition)!! >= totalItemCount!! &&
                        firstVisibleItemPosition >= 0 /*&& totalItemCount >= TOTAL_PAGES*/) {
                        currentPage += 1
                        loadFirstPage()
                    }
                }

            }

        })

        loadFirstPage()
    }
}