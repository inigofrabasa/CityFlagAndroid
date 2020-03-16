package com.inigofrabasa.cityflagandroid.data.repository

import com.inigofrabasa.cityflagandroid.utils.ApiConstants.CONNECTION_TIME_OUT
import com.inigofrabasa.cityflagandroid.utils.ApiConstants.READ_TIME_OUT
import com.inigofrabasa.cityflagandroid.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {
    private val clientBuilder = OkHttpClient.Builder()
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(clientBuilder.build())
        .baseUrl(BuildConfig.API_URL)

    private val retroBuilder = retrofit.build()

    val retrofitService: ServiceApi by lazy {
        retroBuilder.create(ServiceApi::class.java)
    }
}