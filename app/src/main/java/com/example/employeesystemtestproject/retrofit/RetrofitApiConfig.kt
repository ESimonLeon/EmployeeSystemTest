package com.example.employeesystemtestproject.retrofit

import com.example.employeesystemtestproject.BuildConfig
import com.example.employeesystemtestproject.retrofit.services.ServicesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiConfig {

    companion object {

        fun provideRetrofit(): ServicesApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(providesOkHttpClient())
            .build()
            .create(ServicesApi::class.java)

        private fun providesOkHttpClient(): OkHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(providesHttpLoggingInterceptor())
            .build()

        private fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}