package com.example.wallpaper_anime_app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpaper_anime_app.ui.navigation.NavGraph

@Composable
fun WallpaperApp(
) {
    val navController = rememberNavController()
    //init nav host
    NavGraph(navController = navController)
}