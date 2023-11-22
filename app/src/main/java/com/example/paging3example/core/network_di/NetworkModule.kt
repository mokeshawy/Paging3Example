package com.example.paging3example.core.network_di

import android.content.Context
import com.example.paging3example.R
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.gson.gson
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor

class NetworkModule(private val context: Context) {

    val api: HttpClient = HttpClient(OkHttp) {

        expectSuccess = true

        install(ContentNegotiation) { gson() }

        defaultRequest {
            url(context.getString(R.string.base_url))
            header("X-RapidAPI-Key", "3e4182e71fmsha5349893df1249ap1f73bbjsn3b1030841bc4")
            header("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
        }

        engine {
            addInterceptor(HttpLoggingInterceptor { message ->
                Timber.tag("OkHttp").d(message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY))
            addInterceptor(NetLoggingInterceptor())
        }
    }
}