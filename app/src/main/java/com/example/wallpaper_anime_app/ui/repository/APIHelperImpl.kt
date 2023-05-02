package com.example.wallpaper_anime_app.ui.repository

import com.example.wallpaper_anime_app.network.WallpaperAPI
import com.example.wallpaper_anime_app.network.domain.Category
import com.example.wallpaper_anime_app.network.domain.ResultsAnimeItemResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class APIHelperImpl @Inject constructor() :
    APIHelper {
    override suspend fun getCategory(): Flow<Category> {
        return flow {
            emit(WallpaperAPI.retrofitService.getListCategory())
        }
    }

    override suspend fun getItem(route: String): Flow<ResultsAnimeItemResponse> {
        return flow {
            emit(WallpaperAPI.retrofitService.getTypeOther(route))
        }

    }

    override suspend fun getAllDetailItem(
        route: String,
        amount: Int,
    ): Flow<ResultsAnimeItemResponse> {
        return flow {
            emit(WallpaperAPI.retrofitService.getAllDetailItem(route = route, amount = amount))
        }
    }


}