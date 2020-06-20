package com.asif.data

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiEndPoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getRequestHeader(mContext))
            .build()

    companion object {
        private var mInstance: RetrofitService? = null
        lateinit var mContext: Context
        @Synchronized
        fun getInstance(context: Context): RetrofitService {
            mContext = context
            if (mInstance == null) {
                mInstance = RetrofitService(/*context = mContext*/)
            }
            return mInstance!!
        }
    }
    fun getApi(): ApiHelper {
        return retrofit.create<ApiHelper>(ApiHelper::class.java)
    }
    private fun getRequestHeader(mContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }


}