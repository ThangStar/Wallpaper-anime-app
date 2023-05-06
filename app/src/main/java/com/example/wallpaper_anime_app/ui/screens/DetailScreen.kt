@file:OptIn(ExperimentalFoundationApi::class)

package com.example.wallpaper_anime_app.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.wallpaper_anime_app.ui.model.DetailViewModel
import com.example.wallpaper_anime_app.ui.navigation.NavScreens
import com.example.wallpaper_anime_app.ui.theme.BgGradianBettwent
import com.example.wallpaper_anime_app.ui.theme.BgGradianEnd
import com.example.wallpaper_anime_app.ui.theme.BgGradianStart
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenScroll(
    paddingValue: PaddingValues = PaddingValues(horizontal = 16.dp),
    detailViewModel: DetailViewModel,
    nameArgument: String? = "name",
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    BgGradianStart.copy(0.3f), BgGradianBettwent.copy(0.3f), BgGradianEnd.copy(0.3f)
                )
            )
        ),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = nameArgument!!,
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Black.copy(0.2f),
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back stack"
                        )
                    }
                }
            )
        },
        content = { paddingValue: PaddingValues ->
            Spacer(modifier = Modifier.height(62.dp))
            val listItem by detailViewModel.listDetailItem.collectAsState()
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalItemSpacing = 6.dp,
                modifier = Modifier.padding(paddingValue)
            ) {
                itemsIndexed(listItem) { i, item ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(if (i > 0) 240.dp else 210.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(item.url)
                                .crossfade(true)
                                .build()
                        )
                        val state = painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .progressSemantics()
                                        .size(64.dp)
                                )
                            }
                        }
                        AsyncImage(
                            model = painter.request,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val encodedUrl =
                                        URLEncoder.encode(
                                            item.url,
                                            StandardCharsets.UTF_8.toString()
                                        )
                                    navController.navigate(NavScreens.PreviewItemScreen.route + "/" + encodedUrl)
                                },
                            contentScale = ContentScale.Crop,
                            filterQuality = FilterQuality.None
                        )
                    }
                }
                item(span = StaggeredGridItemSpan.FullLine){
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    name: String = "Neko",
    detailViewModel: DetailViewModel = hiltViewModel(),
    nameArgument: String? = "name",
    navController: NavController = rememberNavController(),
) {
    Scaffold(content = { paddingValue ->
        DetailScreenScroll(
            paddingValue, detailViewModel = detailViewModel,
            nameArgument = nameArgument,
            navController = navController
        )
    })
}

@Preview
@Composable
fun PrevDetailScreen() {
    Wallpaper_anime_appTheme {
        DetailScreen()
    }
}