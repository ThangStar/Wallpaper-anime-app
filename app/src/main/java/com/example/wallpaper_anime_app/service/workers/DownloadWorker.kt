package com.example.wallpaper_anime_app.service.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.wallpaper_anime_app.service.workers.utils.makeStatusNotification
import com.example.wallpaper_anime_app.service.workers.utils.saveToStorage

const val TAG = "Worker"
class DownloadWorker(private val context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {
        return try {
//            makeStatusNotification("Download", "Downloading image..", context)
            saveToStorage(context = context)
            Result.success()

        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            Result.failure()
        }

    }
}