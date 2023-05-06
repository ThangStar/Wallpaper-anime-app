package com.example.wallpaper_anime_app.ui.model

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper_anime_app.service.workers.repository.WorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PreviewItemViewModel @Inject constructor(
    val workerRepository: WorkerRepository,
    @ApplicationContext val context: Context,
) : ViewModel() {
    fun startDownloadImage(url: String? = null) {
        if (url != null) {
            workerRepository.startDownloadImage(url)
        }
    }

    fun onSetWallpaper(bitmap: Bitmap?, onFinishSetWallpaper: (() -> Unit?)? = null) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val wallpaperManager = WallpaperManager.getInstance(context)
                wallpaperManager.setBitmap(bitmap)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                    wallpaperManager.addOnColorsChangedListener({ p0, p1 -> // changed wallpaper system success
                        if (onFinishSetWallpaper != null) {
                            onFinishSetWallpaper()
                        }
                        Toast.makeText(context, "changed your wallpaper", Toast.LENGTH_SHORT)
                            .show()
                    }, Handler(Looper.getMainLooper()) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        true
                    })
                }

            }
        }
    }
}