package com.example.wallpaper_anime_app.network.domain

import android.icu.text.CaseMap.Upper
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultsAnimeItemResponse(
    @SerializedName("results")
    val listDetailResponse: List<AnimeItem>,
)

data class AnimeItem(
    @SerializedName("anime_name", alternate = ["artist_name"])
    val animeName: String,

    val url: String,

    @SerializedName("artist_href")
    val artistHref: String?,

    @SerializedName("source_url")
    val sourceUrl: String?,

    var nameRoute: String?
)