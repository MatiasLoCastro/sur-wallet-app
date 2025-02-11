package com.sur_wallet.app.service

import com.sur_wallet.app.model.Transaction
import retrofit2.http.GET

interface TransactionService {

    @GET("products")
    suspend fun getTransactions(): List<Transaction>
}