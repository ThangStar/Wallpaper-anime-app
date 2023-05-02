package com.example.wallpaper_anime_app.ui.data

object Utils {
    sealed class Limit(val sizeList: Int){
        object Five: Limit(sizeList = 5)
    }
}