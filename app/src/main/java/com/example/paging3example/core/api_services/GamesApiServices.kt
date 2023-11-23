package com.example.paging3example.core.api_services

import com.example.paging3example.core.network_di.NetworkModule
import com.example.paging3example.features.fragments.home_fargment.data.response.GamesResponseDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class GamesApiServices(private val networkModule: NetworkModule) {

    suspend fun getAllGames(pageNo: Int, pageSize: Int) = networkModule.api.get {
        url("games?page")
        parameter("pageNo", pageNo)
        parameter("pageSize", pageSize)
    }.body<GamesResponseDto>()
}