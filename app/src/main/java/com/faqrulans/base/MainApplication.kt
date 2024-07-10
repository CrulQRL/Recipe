package com.faqrulans.base

import android.app.Application
import com.faqrulans.base.di.repositoryModule
import com.faqrulans.base.di.useCaseModule
import com.faqrulans.base.di.viewModelModule
import com.faqrulans.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }

}
