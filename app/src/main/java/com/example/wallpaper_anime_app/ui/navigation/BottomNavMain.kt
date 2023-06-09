package com.example.wallpaper_anime_app.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpaper_anime_app.ui.model.HomeViewModel
import com.example.wallpaper_anime_app.ui.screens.HomeScreen
import com.example.wallpaper_anime_app.ui.screens.PreviewItemScreen

val listBottom = listOf<BottomNavScreen>(
    BottomNavScreen.HomeScreen, BottomNavScreen.DownloadScreen, BottomNavScreen.SettingScreen,

    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavMain(
    navController: NavController,
    bottomNavController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(0.dp),
            ) {
                var routerCurrent by remember {
                    mutableStateOf(BottomNavScreen.HomeScreen.route)
                }
                listBottom.forEach {
                    NavigationBarItem(
                        selected = routerCurrent == it.route,
                        onClick = {
                            routerCurrent = it.route
                            bottomNavController.navigate(it.route)
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = it.icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                contentScale = ContentScale.Crop
                            )
                        },
                        label = {
                            Text(text = it.label)
                        },
                        alwaysShowLabel = false,
                    )
                }
            }
        }
    ) { paddingValue ->
        NavHost(
            bottomNavController,
            startDestination = BottomNavScreen.HomeScreen.route,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable(BottomNavScreen.HomeScreen.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                val listPopular by homeViewModel.manyPopular.collectAsState(initial = emptyList())
                HomeScreen(navController = navController, listPopular = listPopular)
            }
            composable(BottomNavScreen.SettingScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(BottomNavScreen.DownloadScreen.route) {
                PreviewItemScreen(navController = navController)
            }

        }
    }

}
