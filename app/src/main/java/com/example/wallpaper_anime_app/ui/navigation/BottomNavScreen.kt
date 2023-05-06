package com.example.wallpaper_anime_app.ui.navigation

import com.example.wallpaper_anime_app.R

sealed class BottomNavScreen
    (
    val label: String,
    val icon: Int,
    val route: String,
) {
    object HomeScreen : BottomNavScreen("Images", R.drawable.image, "home_route")
    object DownloadScreen : BottomNavScreen("Downloaded", R.drawable.download, "download_route")
    object SettingScreen : BottomNavScreen("Setting", R.drawable.settings, "setting_route")
}