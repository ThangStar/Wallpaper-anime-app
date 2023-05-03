package com.example.wallpaper_anime_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallpaper_anime_app.ui.screens.DetailScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavScreens.BottomHomeScreen.route) {
        composable(NavScreens.BottomHomeScreen.route) {
            BottomNavMain(navController)
        }
        composable(
            NavScreens.DetailScreen.route + "/{name}",
            arguments = listOf(navArgument("name"){
                type = NavType.StringType
            })
        ) { it: NavBackStackEntry ->
            DetailScreen(nameArgument = it.arguments?.getString("name"))
        }
    }
}