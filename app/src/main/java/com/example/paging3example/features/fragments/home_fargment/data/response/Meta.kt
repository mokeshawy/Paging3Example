package com.example.paging3example.features.fragments.home_fargment.data.response


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("next_page")
    val nextPage: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)