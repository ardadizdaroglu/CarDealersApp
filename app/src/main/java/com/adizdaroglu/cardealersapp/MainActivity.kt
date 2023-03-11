package com.adizdaroglu.cardealersapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adizdaroglu.cardealersapp.presentation.Screen
import com.adizdaroglu.cardealersapp.presentation.car_list.CarListScreen
import com.adizdaroglu.cardealersapp.presentation.ui.theme.CarDealersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarDealersAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CarListScreen.route
                    ) {
                        composable(
                            route = Screen.CarListScreen.route
                        ) {
                            CarListScreen(navController)
                        }
                    }
                }
            }
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}