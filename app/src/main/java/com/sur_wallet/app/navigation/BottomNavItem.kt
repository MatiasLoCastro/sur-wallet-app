package com.sur_wallet.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {

    object Home : BottomNavItem("HomeScreen/{userId}", Icons.Default.Home, "Home")
    object Transacciones : BottomNavItem(
        "TransaccionesScreen",
        Icons.AutoMirrored.Filled.List, "Transacciones"
    )

    object Tarjetas : BottomNavItem("TarjetasScreen", Icons.Default.CreditCard, "Tarjeta")
    object Servicios : BottomNavItem("ServiciosScreen", Icons.Default.Person, "Servicios")
    object Menu : BottomNavItem("PerfilScreen/{userId}", Icons.Default.Menu, "Perfil")


}