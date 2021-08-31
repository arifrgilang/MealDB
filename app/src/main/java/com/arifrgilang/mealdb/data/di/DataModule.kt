package com.arifrgilang.mealdb.data.di

import com.arifrgilang.mealdb.BuildConfig
import com.arifrgilang.mealdb.data.repository.MealRepositoryImpl
import com.arifrgilang.mealdb.data.service.MealAPI
import com.arifrgilang.mealdb.data.util.RetrofitFactory
import com.arifrgilang.mealdb.domain.MealRepository
import org.koin.dsl.module


/**
 * Created by arifrgilang on 8/31/2021
 */
val dataModule = module {
    single {
        RetrofitFactory.provideHttpLoggingInterceptor(BuildConfig.DEBUG)
    }

    single {
        RetrofitFactory.provideOkHttpClientBuilder(get())
    }

    single {
        RetrofitFactory.provideRetrofitBuilder(BuildConfig.BASE_URL)
    }

    single {
        RetrofitFactory.provideService(
            MealAPI::class.java, get(), get()
        )
    }

    single<MealRepository> {
        MealRepositoryImpl(get())
    }
}