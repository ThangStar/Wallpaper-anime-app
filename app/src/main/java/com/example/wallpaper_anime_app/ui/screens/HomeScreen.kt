@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.wallpaper_anime_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.wallpaper_anime_app.R
import com.example.wallpaper_anime_app.network.domain.AnimeItem
import com.example.wallpaper_anime_app.ui.components.MySearchBar
import com.example.wallpaper_anime_app.ui.navigation.NavScreens
import com.example.wallpaper_anime_app.ui.theme.BgGradianBettwent
import com.example.wallpaper_anime_app.ui.theme.BgGradianEnd
import com.example.wallpaper_anime_app.ui.theme.BgGradianStart
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme


@Composable
fun HomeScreen(
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    navController: NavController,
    listPopular: List<AnimeItem> = listOf(
        AnimeItem(animeName = "name", url = "url.jpg", null, null, "name"),
        AnimeItem(animeName = "name", url = "url.jpg", null, null, "name"),
        AnimeItem(animeName = "name", url = "url.jpg", null, null, "name"),
        AnimeItem(animeName = "name", url = "url.jpg", null, null, "name"),
    ),
) {
    LazyColumn(
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
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Column {
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
        items(listPopular) { it ->
            OtherCategory(navController = navController, it)
        }
        item(
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

@OptIn(ExperimentalMaterial3Api::class)
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
            modifier = Modifier.size(width = 112.dp, height = 186.dp),
            onClick = {
                navController.navigate(NavScreens.DetailScreen.route + "/" + animeItem.nameRoute)
            },
        ) {

            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(animeItem.url)
                    .crossfade(true).build()
            )
            AsyncImage(
                model = imagePainter.request,
                contentDescription = animeItem.animeName,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.baka_loading),
                modifier = Modifier.fillMaxSize(),
                filterQuality = FilterQuality.None
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
    animeItem: AnimeItem = AnimeItem(
        "むらさめしん", "url", artistHref = null, sourceUrl = null, nameRoute = "Baka"
    ),
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(180.dp)
            .clickable {
                navController.navigate(NavScreens.DetailScreen.route + "/" + animeItem.nameRoute)
            },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(animeItem.url).crossfade(true)
                .build(),
            contentDescription = animeItem.animeName,
            contentScale = ContentScale.Crop,

            filterQuality = FilterQuality.Low,
            placeholder = painterResource(id = R.drawable.baka_loading),
        )

        Box(
            contentAlignment = Alignment.BottomStart, modifier = Modifier.background(
                Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Black)
                )
            )
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface.copy(0.3f)
            ) {
                Box(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp)
                ) {
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            ItemCircle(Color.Red)
                            ItemCircle(Color.Yellow)
                            ItemCircle(Color.Green)

                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${animeItem.nameRoute}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Author: ${animeItem.animeName}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                textAlign = TextAlign.Center, color = Color.White.copy(0.6f)
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCircle(
    color: Color,
) {
    Surface(
        modifier = Modifier.size(12.dp), shape = CircleShape
    ) {
        Box(modifier = Modifier.background(color))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevHomeScreen() {
    Wallpaper_anime_appTheme {
        HomeScreen(navController = rememberNavController())
    }
}


@Preview
@Composable
fun PrevOtherCategory() {
    Wallpaper_anime_appTheme() {
        OtherCategory(rememberNavController())
    }
}

@Preview
@Composable
fun PrevOtherCategoryDark() {
    Wallpaper_anime_appTheme(darkTheme = true) {
        OtherCategory(rememberNavController())
    }
}