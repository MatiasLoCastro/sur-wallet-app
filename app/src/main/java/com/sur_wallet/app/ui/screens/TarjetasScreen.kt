package com.sur_wallet.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sur_wallet.app.ui.viewmodel.CardViewModel

@Composable
fun TarjetasScreen(navController: NavController, cardViewModel: CardViewModel = viewModel()) {

    val cards by cardViewModel.cards.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mi Tarjeta",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        if (cards.isNotEmpty()) {
            cards.forEach { card ->
                CreditCardView(
                    cardNumber = card.cardNumber,
                    cardHolder = card.cardHolder,
                    expiryDate = card.expiryDate
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        } else {

            Text(text = "No Hay tarjetas disponibles para mostrar")

        }
    }
}

@Composable
fun CreditCardView(cardNumber: String, cardHolder: String, expiryDate: String) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Green)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "CREDIT CARD",
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = cardNumber,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Card Holder",
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.LightGray)
                    )
                    Text(
                        text = cardHolder,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }

                Column {
                    Text(
                        text = "Expiry",
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.LightGray)
                    )
                    Text(
                        text = expiryDate,
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.White)
                    )
                }
            }
        }
    }
}
