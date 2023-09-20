package com.example.paging3example.fragments.home_fargment.data.response


import com.google.gson.annotations.SerializedName

data class GamesResponseDto(
    @SerializedName("data")
    val gamesResponse: List<GamesResponse>,
    @SerializedName("meta")
    val meta: Meta
)