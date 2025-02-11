package com.sur_wallet.app.model

import android.media.Rating

data class Transaction(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating?
)

data class Rating(
    val rate: Double,
    val count: Int
)