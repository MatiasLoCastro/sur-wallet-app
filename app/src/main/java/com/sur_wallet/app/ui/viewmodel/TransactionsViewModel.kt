package com.sur_wallet.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sur_wallet.app.model.Transaction
import com.sur_wallet.app.service.RetrofitImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions = _transactions.asStateFlow()

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch {
            try {
                val response = RetrofitImpl.transaction.getTransactions()
                _transactions.value = response
                println("TRANSACCIONES ENVIADAS")
                Log.d("API_RESPONSE", "Fetched products: $response")
            } catch (e: Exception) {
                Log.e("ERROR CONSULTANDO TRANSACCIONES}", e.stackTraceToString())
            }
        }
    }


}