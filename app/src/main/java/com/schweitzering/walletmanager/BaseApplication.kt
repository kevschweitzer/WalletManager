package com.schweitzering.walletmanager

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.schweitzering.data.inject.dataModule
import com.schweitzering.walletmanager.inject.appModule
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(listOf(appModule, dataModule))
        }
    }


}