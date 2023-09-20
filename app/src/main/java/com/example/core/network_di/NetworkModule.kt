package com.example.core.network_di

import android.content.Context
import com.example.core.api_services.GamesApiServices
import com.example.paging3example.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @ActivityRetainedScoped
    @Provides
    fun provideGymsApiServices(retrofit: Retrofit): GamesApiServices =
        retrofit.create(GamesApiServices::class.java)


    @Provides
    @Named("AuthInterceptor")
    fun provideAuthInterceptor(): Interceptor =
        Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            newBuilder.addHeader(
                "X-RapidAPI-Key",
                "3e4182e71fmsha5349893df1249ap1f73bbjsn3b1030841bc4"
            )
            newBuilder.addHeader("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
            newBuilder.build().let { chain.proceed(it) }
        }


    @Provides
    @Named("LoggingOkHttpClient")
    fun provideOkhttpClientWithLogging(
    ): OkHttpClient = OkHttpClient.Builder().build()


    @ActivityRetainedScoped
    @Provides
    @Named("GeneralOkHttpClient")
    fun provideGeneralNetworkingClient(
        @Named("LoggingOkHttpClient")
        okHttpClient: OkHttpClient,
        @Named("AuthInterceptor")
        authInterceptor: Interceptor,
    ): OkHttpClient = okHttpClient.newBuilder()
        .addInterceptor(authInterceptor)
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .build()


    @ActivityRetainedScoped
    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        @Named("GeneralOkHttpClient")
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}