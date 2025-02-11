package com.sur_wallet.app.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {

    private const val BASE_URL = "https://fakestoreapi.com/"

    val transaction: TransactionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TransactionService::class.java)
    }

}