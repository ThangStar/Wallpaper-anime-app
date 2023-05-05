package com.example.wallpaper_anime_app.ui.navigation

sealed class NavScreens(
    val route: String,
) {
    object BottomHomeScreen : NavScreens("bottom_home_screen")
    object DetailScreen : NavScreens("detail_screen")
    object PreviewItemScreen : NavScreens("preview_item_screen")

}