package com.arifrgilang.mealdb

import android.app.Application
import com.arifrgilang.mealdb.data.di.dataModule
import com.arifrgilang.mealdb.domain.di.domainModule
import com.arifrgilang.mealdb.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 * Created by arifrgilang on 8/31/2021
 */
class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@AppController)
            modules(
                dataModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }

    companion object {
        lateinit var instance: AppController
    }
}