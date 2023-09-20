package com.example.paging3example.features.fragments.home_fargment.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.paging3example.core.api_services.GamesApiServices
import com.example.paging3example.features.fragments.home_fargment.data.paging_source.GamesPagingSource
import com.example.paging3example.features.fragments.home_fargment.data.paging_source.PAGE_SIZE
import com.example.paging3example.features.fragments.home_fargment.domain.model.GamesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gamesApiServices: GamesApiServices) :
    ViewModel() {

    fun getPaginatedGamesList(): Flow<PagingData<GamesEntity>> {
        return Pager(PagingConfig(PAGE_SIZE, 10)) {
            GamesPagingSource(gamesApiServices)
        }.flow.map { pagingData ->
            pagingData.map { GamesEntity(it.id, it.homeTeam.fullName, it.date) }
        }.cachedIn(viewModelScope)
    }
}