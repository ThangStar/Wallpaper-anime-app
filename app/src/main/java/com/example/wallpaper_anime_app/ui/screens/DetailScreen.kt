@file:OptIn(ExperimentalFoundationApi::class)

package com.example.wallpaper_anime_app.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wallpaper_anime_app.R
import com.example.wallpaper_anime_app.ui.model.DetailViewModel
import com.example.wallpaper_anime_app.ui.theme.BgGradianBettwent
import com.example.wallpaper_anime_app.ui.theme.BgGradianEnd
import com.example.wallpaper_anime_app.ui.theme.BgGradianStart
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreenScroll(
    paddingValue: PaddingValues = PaddingValues(horizontal = 16.dp),
    name: String = "Neko",
    detailViewModel: DetailViewModel,
) {
    Column(
        modifier = Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    BgGradianStart.copy(0.3f), BgGradianBettwent.copy(0.3f), BgGradianEnd.copy(0.3f)
                )
            )
        )
    ) {
        Spacer(modifier = Modifier.height(34.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = name,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        val listItem by detailViewModel.listDetailItem.collectAsState()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalItemSpacing = 6.dp,
        ) {
            itemsIndexed(listItem) { i, item ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (i > 0) 240.dp else 210.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    name: String = "Neko",
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    Scaffold(content = { paddingValue ->
        DetailScreenScroll(paddingValue, detailViewModel = detailViewModel)
    })
}

@Preview
@Composable
fun PrevDetailScreen() {
    Wallpaper_anime_appTheme {
        DetailScreen()
    }
}