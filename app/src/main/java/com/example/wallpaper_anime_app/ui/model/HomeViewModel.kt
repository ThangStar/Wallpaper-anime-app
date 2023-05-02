package com.example.wallpaper_anime_app.ui.model

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
import java.util.Locale
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

const val TAG = "HOME_VIEW_MODEL"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val APIHelper: APIHelper,
) : ViewModel() {

    private var _manyPopular = MutableStateFlow<List<AnimeItem>>(emptyList())
    val manyPopular = _manyPopular

    init {
        initOther()
    }

    private fun initOther() {
        viewModelScope.launch {
            // allCategory: tất cả danh mục
            withContext(Dispatchers.IO) {
                val allCategory = APIHelper.getCategory().collect { array ->
                    array::class.memberProperties.forEach {
                        val typeItem = APIHelper.getItem(it.name)
                        typeItem.collect { it2: ResultsAnimeItemResponse ->
                            it2.listDetailResponse[0].nameRoute = it.name.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            }
                            _manyPopular.value += it2.listDetailResponse
                        }
                    }
                }
            }
        }
    }
}
