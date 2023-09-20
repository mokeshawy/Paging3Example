package com.example.core.api_services

import com.example.paging3example.fragments.home_fargment.data.response.GamesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiServices {

    @GET("games?page")
    suspend fun getAllGames(
        @Query("pageNo") pageNo: Int,
        @Query("pageSize") pageSize: Int
    ): Response<GamesResponseDto>
}