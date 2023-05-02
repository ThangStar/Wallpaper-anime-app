package com.example.wallpaper_anime_app.ui.navigation

import com.example.wallpaper_anime_app.R

sealed class BottomNavScreen
    (
    val label: String,
    val icon: Int,
    val route: String,
) {
    object HomeScreen : BottomNavScreen("Home", R.drawable.image, "home_route")
    object DownloadScreen : BottomNavScreen("Download", R.drawable.download, "download_route")
    object ProfileScreen : BottomNavScreen("Profile", R.drawable.user, "profile_route")
}