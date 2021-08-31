package com.arifrgilang.mealdb.data.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by arifrgilang on 8/31/2021
 */
object RetrofitFactory {

    fun provideHttpLoggingInterceptor(
        isDebug: Boolean
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    fun provideOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)

    fun provideRetrofitBuilder(
        baseUrl: String
    ): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())

    fun <S> provideService(
        serviceClass: Class<S>,
        httpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): S {
        retrofitBuilder.client(httpClientBuilder.build())
        return retrofitBuilder.build().create(serviceClass)
    }

    private const val DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ssZ"
    private const val DEFAULT_CONNECT_TIME_OUT: Long = 30 * 1000
    private const val DEFAULT_READ_TIME_OUT: Long = 30 * 1000

}