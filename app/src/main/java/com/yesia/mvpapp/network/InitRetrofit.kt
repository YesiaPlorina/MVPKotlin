package com.imastudio.customerapp.network

import com.google.gson.GsonBuilder
import com.yesia.mvpapp.helper.Helper.Companion.BASE_URL
import com.yesia.mvpapp.helper.Helper.Companion.BASE_WISATA_URL
import com.yesia.mvpapp.network.RestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object InitRetrofit {

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()
    val gson = GsonBuilder().setLenient().create()
//URL Login dan register
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance(): RestApi = retrofit.create(RestApi::class.java)
//url wisata
    val retrofitwisata = Retrofit.Builder()
        .baseUrl(BASE_WISATA_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstancewisata(): RestApi = retrofitwisata.create(RestApi::class.java)




}