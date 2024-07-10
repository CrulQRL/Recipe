package com.faqrulans.data.di

import com.faqrulans.data.remote.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


val networkModule = module {
    single<OkHttpClient> { provideHttpClient() }
    single<Retrofit> { provideRetrofit(get()) }
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}
