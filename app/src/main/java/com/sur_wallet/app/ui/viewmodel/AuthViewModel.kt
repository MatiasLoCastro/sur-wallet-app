package com.sur_wallet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlin.concurrent.timerTask

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun loginUser(username: String,
                  password: String,
                  navController: NavController,
                  onResult: (Boolean, String, String?) -> Unit) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { event ->
                if(event.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    println("Login successful: ${auth.currentUser?.email}")
                    onResult(true, "Login successful", userId)
                    userId?.let {
                        navController.navigate("homeScreen/$it") {
                            popUpTo("loginScreen") { inclusive = true }
                        }

                    }
                } else {
                    event.exception?.message?.let { onResult(false, it, null) }
                }
            }

    }

    fun registerUser(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { event ->
                if(event.isSuccessful) {
                    onResult(true, "Registration successful")
                } else {
                    event.exception?.message?.let { onResult(false, it) }
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
    }

    fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener() { event ->
                if(event.isSuccessful) {
                    onResult(true, "Password reset email sent")
                } else {
                    onResult(false, event.exception?.message)
                }
            }
    }

}