package com.example.wallpaper_anime_app.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallpaper_anime_app.network.domain.AnimeItem
import kotlin.math.max

class PagingImageOther: PagingSource<Int, AnimeItem>() {
    private fun ensureValidKey(key: Int): Int {
        val STARTING_KEY = 1
        return max(STARTING_KEY, key)
    }
    override fun getRefreshKey(state: PagingState<Int, AnimeItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeItem> {
        TODO("Not yet implemented")
    }

}