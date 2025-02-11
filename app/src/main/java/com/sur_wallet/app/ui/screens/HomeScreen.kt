package com.sur_wallet.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sur_wallet.app.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    userId: String,
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {

    val userData by homeViewModel.userData.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getUserDataFromFireStoreDb(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "\uD83D\uDC4B Hola ${userData.name}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Último acceso: ${userData.lastAccess}",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF00C853)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "**** **** **** ${userData.cardNumber.takeLast(4)}",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "Expira: ${userData.cardExpiry}",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "SALDO DISPONIBLE", fontSize = 16.sp, color = Color.Gray)
        Text(
            text = "\$${"%,.2f".format(userData.balance)}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF512DA8)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "CARGAR DINERO")
            }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("EXTRAER DINERO")
            }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("SEGUIR MI PRÉSTAMO")
            }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("RECARGA SUBE")
            }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("RECARGA CELULAR")
            }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("PAGAR SERVICIO")
            }
        }
    }

}
