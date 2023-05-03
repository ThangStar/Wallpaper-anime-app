package com.example.wallpaper_anime_app.ui.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper_anime_app.network.domain.AnimeItem
import com.example.wallpaper_anime_app.network.domain.ResultsAnimeItemResponse
import com.example.wallpaper_anime_app.ui.repository.APIHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiHelper: APIHelper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _listDetailItem = MutableStateFlow<List<AnimeItem>>(emptyList())
    val listDetailItem = _listDetailItem

    private val nameArgument = checkNotNull(savedStateHandle["name"])

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val flowData = apiHelper.getAllDetailItem("$nameArgument", 16)
                flowData.collect { it: ResultsAnimeItemResponse ->
                    _listDetailItem.value += it.listDetailResponse
                }
            }
        }
    }

}