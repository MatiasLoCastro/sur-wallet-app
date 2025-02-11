package com.sur_wallet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.sur_wallet.app.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData


    fun getUserDataFromFireStoreDb(userId: String) {
        viewModelScope.launch {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null)
                        _userData.value = UserData(
                            name = document.getString("name") ?: "",
                            lastAccess = Timestamp.now().toDate().toString(),
                            cardNumber = document.getString("cardNumber") ?: "",
                            cardExpiry = document.getString("cardExpiry") ?: "",
                            balance = document.getDouble("balance") ?: 0.0,
                            dni = document.getString("DNI") ?: ""
                        )
                }
                .addOnFailureListener {
                    println("ERROR GETTING USER DATA ${it.message}")
                }
        }

    }
    
}