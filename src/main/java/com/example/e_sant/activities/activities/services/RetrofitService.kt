package com.example.e_sant.activities.activities.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://d167aebfb18e.ngrok.io"

    private val okHttpClient = OkHttpClient.Builder().build()

    val instance: Endpoint by lazy{

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Endpoint::class.java)
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://d167aebfb18e.ngrok.io")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}