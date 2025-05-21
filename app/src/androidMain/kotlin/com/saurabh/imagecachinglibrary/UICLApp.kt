package com.saurabh.imagecachinglibrary

import android.app.Application
import com.saurabh.imagecachinglibrary.di.androidModule
import com.saurabh.imagecachinglibrary.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UICLApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UICLApp)
            modules(commonModule, androidModule) // androidModule will be created next
        }
    }
}