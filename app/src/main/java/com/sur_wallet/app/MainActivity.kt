package com.sur_wallet.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sur_wallet.app.navigation.BottomNavigationBar
import com.sur_wallet.app.ui.screens.CargaExitosaScreen
import com.sur_wallet.app.ui.screens.HomeScreen
import com.sur_wallet.app.ui.screens.LoginScreen
import com.sur_wallet.app.ui.screens.PerfilScreen
import com.sur_wallet.app.ui.screens.ServiciosScreen
import com.sur_wallet.app.ui.screens.TarjetasScreen
import com.sur_wallet.app.ui.screens.TransaccionesScreen
import com.sur_wallet.app.ui.theme.SurWalletTheme
import com.sur_wallet.app.ui.viewmodel.AuthViewModel
import com.sur_wallet.app.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels() // aca creo la instancia de ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SurWalletTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) } // agrego la bottom nav
                ) { innerPadding ->
                    val homeViewModel: HomeViewModel = viewModel()
                    NavHost(
                        navController = navController,
                        startDestination = "loginScreen",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("loginScreen") {
                            LoginScreen(
                                authViewModel = AuthViewModel(),
                                navController = navController
                            )
                        }
                        composable("HomeScreen/{userId}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId") ?: ""
                            HomeScreen(userId, navController, homeViewModel)
                        }


                        composable("PerfilScreen/{userId}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId") ?: ""
                            PerfilScreen(userId, navController, homeViewModel)
                        }
                        composable("TransaccionesScreen") { TransaccionesScreen(navController) }
                        composable("TarjetasScreen") { TarjetasScreen(navController) }
                        composable("ServiciosScreen") { ServiciosScreen(navController) }
                        composable("success") { CargaExitosaScreen(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SurWalletTheme {
        Greeting("Android")
    }
}