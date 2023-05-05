package com.example.wallpaper_anime_app.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.wallpaper_anime_app.R
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme
import com.example.wallpaper_anime_app.ui.theme.White900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewItemScreen(
    navController: NavController = rememberNavController(),
    keyCacheArgument: String? = "",
    onStartDownload: () -> Unit = {},
    onSetWallpaper: (bitmap: Bitmap?) -> Unit = {},
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(keyCacheArgument)
            .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )
    val imageState = imagePainter.state

    Scaffold(content = { paddingValue: PaddingValues ->
        AsyncImage(
            model =
            imagePainter.request,
            contentDescription = "setting",
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize(),
            placeholder = painterResource(id = R.drawable.baka_loading),
            filterQuality = FilterQuality.High,

            )
        TopBarViewItem(navController, onStartDownload, onSetWallpaper, imageState)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarViewItem(
    navController: NavController,
    onStartDownload: () -> Unit = {},
    onSetWallpaper: (bitmap: Bitmap?) -> Unit = {},
    imageState: AsyncImagePainter.State,
) {

    TopAppBar(title = {
    },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {

                Icon(
                    tint = White900,
                    imageVector = Icons.Default.ArrowBack, contentDescription = "back stack"
                )
            }

        }, colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Black.copy(0.2f),
        ), actions = {

            var isExpand by remember {
                mutableStateOf(false)
            }
            IconButton(onClick = { isExpand = !isExpand }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "back stack",
                    tint = White900
                )
            }
            DropdownMenu(expanded = isExpand,
                onDismissRequest = { isExpand = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Download")
                    },
                    onClick = {
                        //do something set Download
                        onStartDownload()
                    })


                DropdownMenuItem(
                    text = {
                        Text(text = "Set wallpaper")
                    },
                    onClick = {
                        if (
                            imageState is AsyncImagePainter.State.Success
                        ) {
                            val bm = imageState.result.drawable.toBitmap()
                            onSetWallpaper(bm)
                        }
                    })
            }
        })
}

@Preview
@Composable
fun PrevPreviewItemScreen() {
    Wallpaper_anime_appTheme {
        PreviewItemScreen()
    }

}

@Preview
@Composable
fun PrevDarkPreviewItemScreen() {
    Wallpaper_anime_appTheme(darkTheme = true) {
        PreviewItemScreen()
    }

}