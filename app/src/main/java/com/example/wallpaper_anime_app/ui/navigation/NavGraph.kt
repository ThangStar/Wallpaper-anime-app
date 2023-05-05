package com.example.wallpaper_anime_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.wallpaper_anime_app.ui.model.PreviewItemViewModel
import com.example.wallpaper_anime_app.ui.screens.DetailScreen
import com.example.wallpaper_anime_app.ui.screens.PreviewItemScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavScreens.BottomHomeScreen.route) {
        composable(NavScreens.BottomHomeScreen.route) {
            BottomNavMain(navController)
        }
        composable(
            NavScreens.DetailScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) { it: NavBackStackEntry ->
            DetailScreen(
                nameArgument = it.arguments?.getString("name"),
                navController = navController
            )
        }
        composable(
            NavScreens.PreviewItemScreen.route + "/{key-cache}",
            arguments = listOf(navArgument("key-cache") {
                type = NavType.StringType
            })
        ) { it: NavBackStackEntry ->


            val painterWallpaper = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("baseImage")
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .build()
            )

            val viewModel: PreviewItemViewModel = hiltViewModel()
            val onStartDownload = viewModel::startDownloadImage
            val onSetWallpaper = viewModel::onSetWallpaper
            PreviewItemScreen(
                navController,
                keyCacheArgument = it.arguments?.getString("key-cache"),
                onStartDownload = onStartDownload,
                onSetWallpaper = onSetWallpaper,
            )
        }
    }
}