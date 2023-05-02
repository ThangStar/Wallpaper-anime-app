package com.example.wallpaper_anime_app.network.repository

import com.example.wallpaper_anime_app.network.domain.AnimeItem
import com.example.wallpaper_anime_app.network.domain.Category
import com.example.wallpaper_anime_app.network.domain.ResultsAnimeItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitRepository {
    @GET("endpoints")
    suspend fun getListCategory(): Category

    @GET()
    suspend fun getTypeOther(@Url route: String): ResultsAnimeItemResponse

    // limit default: 16
    @GET
    suspend fun getAllDetailItem(@Url route: String, @Query("amount") amount: Int): ResultsAnimeItemResponse
}