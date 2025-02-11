package com.sur_wallet.app.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.sur_wallet.app.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Objects

class CardViewModel : ViewModel() {

    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    init {
        getUserCards()
    }

    private fun getUserCards() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (Objects.isNull(currentUser)) {
            Log.e("CardViewModel", "ERROR login getUserCards()")
            return
        }

        if (currentUser != null) {
            FirebaseFirestore.getInstance()
                .collection("cards")
                .whereEqualTo(FieldPath.documentId(), currentUser.uid)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.e("CardViewModel", "ERROR getUserCards() de firebase", error)
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val cardsList = snapshot.documents.mapNotNull { document ->
                            document.toObject(Card::class.java)
                        }
                        _cards.value = cardsList
                    }
                }
        }

    }


}