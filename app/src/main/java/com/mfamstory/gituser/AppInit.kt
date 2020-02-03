package com.mfamstory.gituser

import android.app.Application
import com.mfamstory.gituser.di.databaseModule
import com.mfamstory.rxmvvmtest.di.apiModule
import com.mfamstory.rxmvvmtest.di.networkModule
import com.mfamstory.rxmvvmtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppInit : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(databaseModule)
            modules(networkModule)
            modules(apiModule)
            modules(viewModelModule)
        }
    }
}