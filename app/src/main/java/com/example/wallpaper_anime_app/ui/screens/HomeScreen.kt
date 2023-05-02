@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.wallpaper_anime_app.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.wallpaper_anime_app.R
import com.example.wallpaper_anime_app.network.domain.AnimeItem
import com.example.wallpaper_anime_app.ui.components.MySearchBar
import com.example.wallpaper_anime_app.ui.model.HomeViewModel
import com.example.wallpaper_anime_app.ui.navigation.NavScreens
import com.example.wallpaper_anime_app.ui.theme.BgGradianBettwent
import com.example.wallpaper_anime_app.ui.theme.BgGradianEnd
import com.example.wallpaper_anime_app.ui.theme.BgGradianStart
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme
import com.example.wallpaper_anime_app.ui.theme.White900


@Composable
fun HomeScreen(
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val listPopular by homeViewModel.manyPopular.collectAsState(initial = emptyList())
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        BgGradianStart.copy(0.3f),
                        BgGradianBettwent.copy(0.3f),
                        BgGradianEnd.copy(0.3f)
                    )
                )
            ),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item(span = {
            GridItemSpan(maxCurrentLineSpan)
        }) {
            Column() {
                Spacer(modifier = Modifier.height(42.dp))
                MySearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Phổ biến",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.height(12.dp))
                ListCardItemPopular(listPopular, navController)
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Khác",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium),
                )
            }
        }
        itemsIndexed(listPopular) { i, it ->
            Box(
                modifier =
                if (i % 2 == 0) Modifier.padding(start = 16.dp) else Modifier.padding(end = 16.dp)
            ) {
                OtherCategory(navController = navController, it)
            }
        }
        item(
            span = {
                GridItemSpan(3)
            }
        ) {
            Spacer(modifier = Modifier.height(42.dp))
        }
    }
}

@Composable
fun ListCardItemPopular(listPopular: List<AnimeItem>, navController: NavController) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(26.dp),
    ) {
        items(listPopular) { it: AnimeItem ->
            CardItemPopular(navController, it)
        }
    }
}

@Composable
fun CardItemPopular(
    navController: NavController,

    animeItem: AnimeItem,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            onClick = {
                navController.navigate(NavScreens.DetailScreen.route)
            }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animeItem.url)
                    .crossfade(true)
                    .build(),
                contentDescription = animeItem.anime_name,
                modifier = Modifier
                    .size(width = 112.dp, height = 186.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.baka_loading),

                )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${animeItem.nameRoute}",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 22.sp
        )
    }
}

@Composable
fun OtherCategory(
    navController: NavController,
    animeItem: AnimeItem,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable {
                navController.navigate(NavScreens.DetailScreen.route)
            }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animeItem.url)
                    .crossfade(true)
                    .build(),
                contentDescription = animeItem.anime_name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(76.dp),
                filterQuality = FilterQuality.Low,
                placeholder = painterResource(id = R.drawable.baka_loading)
            )
            Text(
                text = "${animeItem.nameRoute}",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = White900,
                    textAlign = TextAlign.Center
                )
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevHomeScreen() {
    Wallpaper_anime_appTheme {
        HomeScreen(navController = rememberNavController())
    }
}