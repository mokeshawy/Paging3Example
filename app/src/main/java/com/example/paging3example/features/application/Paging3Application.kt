package com.example.paging3example.features.application

import android.app.Application
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger
import com.example.paging3example.BuildConfig
import com.example.paging3example.core.di.networkModule
import com.example.paging3example.core.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Paging3Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@Paging3Application)
            modules(networkModule,viewModelsModule)
        }
        plantTimberTrees()
        initRemoteDebugger()
    }


    private fun plantTimberTrees() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.plant(RemoteDebuggerTree())
        }
    }


    private fun initRemoteDebugger() {
        if (!BuildConfig.DEBUG) return
        val remoteDebugger =
            AndroidRemoteDebugger.Builder(applicationContext).disableInternalLogging()
                .port(getRemoteDebuggerPort()).build()
        AndroidRemoteDebugger.init(remoteDebugger)
    }

    private fun getRemoteDebuggerPort() = 9094

    private inner class RemoteDebuggerTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            AndroidRemoteDebugger.Log.log(priority, tag, message, t)
        }
    }
}