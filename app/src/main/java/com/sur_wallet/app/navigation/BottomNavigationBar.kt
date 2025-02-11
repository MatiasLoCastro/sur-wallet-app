package com.sur_wallet.app.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.firebase.auth.FirebaseAuth


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Tarjetas,
        BottomNavItem.Servicios,
//        BottomNavItem.Menu
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {

        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        BottomNavigationItem(
            icon = { Icon(BottomNavItem.Home.icon, contentDescription = BottomNavItem.Home.label) },
            label = { Text(text = BottomNavItem.Home.label) },
            selected = currentRoute == BottomNavItem.Home.route,
            onClick = {
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                navController.navigate("HomeScreen/$userId") {
                    popUpTo(navController.graph.startDestinationId) { saveState =  true } // usamos saveState para prevenir instancias duplicadas
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        BottomNavigationItem(
            icon = { Icon(BottomNavItem.Transacciones.icon, contentDescription = BottomNavItem.Transacciones.label) },
            label = { Text(text = BottomNavItem.Transacciones.label) },
            selected = currentRoute == BottomNavItem.Transacciones.route,
            onClick = {
                navController.navigate(BottomNavItem.Transacciones.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                selectedContentColor = Color(0xFF00C853),
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        BottomNavigationItem(
            icon = { Icon(BottomNavItem.Menu.icon, contentDescription = BottomNavItem.Menu.label) },
            label = { Text(text = BottomNavItem.Menu.label) },
            selected = currentRoute == BottomNavItem.Menu.route,
            onClick = {
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                navController.navigate("PerfilScreen/$userId") {
                    popUpTo(navController.graph.startDestinationId) { saveState =  true } // usamos saveState para prevenir instancias duplicadas
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

    }





}