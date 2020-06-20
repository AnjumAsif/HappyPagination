package com.asif.data.response


import com.google.gson.annotations.SerializedName

data class PaginationResponseX(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Results>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)