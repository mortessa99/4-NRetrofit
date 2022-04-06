package com.example.a4_nretrofit.server

import com.example.a4_nretrofit.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var retrofit: Retrofit
    private val addMoreSettingToRetrofitByOkhttp = OkHttpClient.Builder()
                                            .connectTimeout(60,TimeUnit.SECONDS)
                                            .readTimeout(60,TimeUnit.SECONDS)
                                            .writeTimeout(60,TimeUnit.SECONDS)
                                            .build()


    fun getClient():Retrofit{
        retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //بدون این هم کار میکند ولی بنویسید
                    .client(addMoreSettingToRetrofitByOkhttp)  //بدون این هم کار میکند ولی بنویسید
                    .build()
                    //.create()

        return retrofit
    }
}