package com.example.wallpaper_anime_app.network

import com.example.wallpaper_anime_app.network.domain.Category
import com.example.wallpaper_anime_app.network.repository.RetrofitRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://nekos.best/api/v2/"
var gson: Gson = GsonBuilder()
    .setLenient()
    .create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()
object WallpaperAPI {
    val retrofitService: RetrofitRepository =
        retrofit.create(RetrofitRepository::class.java)
}