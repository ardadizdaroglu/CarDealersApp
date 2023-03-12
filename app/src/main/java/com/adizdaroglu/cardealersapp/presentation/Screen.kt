package com.adizdaroglu.cardealersapp.presentation

sealed class Screen(val route: String) {
    object CarListScreen: Screen("car_list_screen")
    object CarDetailsScreen: Screen("car_details_screen")
}