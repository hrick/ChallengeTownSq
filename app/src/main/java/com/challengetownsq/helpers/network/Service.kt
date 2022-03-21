package com.challengetownsq.helpers.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class Service {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkhttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    fun <API> createService(apiClass: Class<API>): API {
        return retrofit.create(apiClass)
    }

    fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val timeout: Long = 10
        val client = OkHttpClient.Builder()

        client.networkInterceptors().add(logging)
        client.connectTimeout(timeout, TimeUnit.SECONDS)
        client.writeTimeout(timeout, TimeUnit.MINUTES)
        client.readTimeout(timeout, TimeUnit.MINUTES)

        return client.build()
    }
}