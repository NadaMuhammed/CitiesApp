package com.example.cities.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cities.navigation.Routes.CITIES
import com.example.cities.ui.screens.CitiesScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = CITIES) {
        composable(CITIES) {
            CitiesScreen(navHostController = navHostController)
        }
    }
}