package com.sur_wallet.app.model

data class UserData(
    val name: String = "",
    val lastAccess: String = "",
    val cardNumber: String = "",
    val cardExpiry: String = "",
    val balance: Double = 0.0,
    val dni: String = ""
)