package com.schweitzering.walletmanager

import android.app.Application
import com.schweitzering.data.inject.dataModule
import com.schweitzering.walletmanager.inject.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(listOf(appModule, dataModule))
        }
    }


}