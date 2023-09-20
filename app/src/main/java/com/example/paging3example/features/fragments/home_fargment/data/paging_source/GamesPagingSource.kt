package com.example.paging3example.features.fragments.home_fargment.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3example.core.api_services.GamesApiServices
import com.example.paging3example.features.fragments.home_fargment.data.response.GamesResponse

const val PAGE_SIZE = 10

class GamesPagingSource(private val gamesApiServices: GamesApiServices) :
    PagingSource<Int, GamesResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GamesResponse> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response =
                gamesApiServices.getAllGames(pageNo = nextPageNumber, pageSize = PAGE_SIZE)
            LoadResult.Page(
                data = response.body()?.gamesResponse ?: emptyList(),
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GamesResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}