package com.asif.data

import com.asif.data.response.PaginationResponseX
import io.reactivex.Observable
import retrofit2.http.*

interface ApiHelper {


    @GET("movie/popular")
    fun getPaginationData(@Query("api_key")  apiKey:String,@Query("page")  page:Long): Observable<PaginationResponseX>

}