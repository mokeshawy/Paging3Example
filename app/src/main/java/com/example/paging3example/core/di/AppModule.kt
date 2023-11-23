package com.example.paging3example.core.di

import com.example.paging3example.core.api_services.GamesApiServices
import com.example.paging3example.core.network_di.NetworkModule
import com.example.paging3example.features.fragments.home_fargment.domain.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    single { NetworkModule(get()) }
    single { GamesApiServices(get()) }
}


val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
}


val repositoryModule = module {
    scope<HomeViewModel> { }
}