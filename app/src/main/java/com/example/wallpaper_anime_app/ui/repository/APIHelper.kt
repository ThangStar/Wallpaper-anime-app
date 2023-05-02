package com.example.wallpaper_anime_app.ui.repository

import com.example.wallpaper_anime_app.network.domain.Category
import com.example.wallpaper_anime_app.network.domain.ResultsAnimeItemResponse
import kotlinx.coroutines.flow.Flow

interface APIHelper {
    suspend fun getCategory(): Flow<Category>
    suspend fun getItem(route: String): Flow<ResultsAnimeItemResponse>
    suspend fun getAllDetailItem(route: String, amount: Int):  Flow<ResultsAnimeItemResponse>
}