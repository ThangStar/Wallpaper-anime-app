package com.example.wallpaper_anime_app.service.workers.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.wallpaper_anime_app.service.workers.DownloadWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WorkerRepositoryImpl
@Inject constructor(
    @ApplicationContext
    context: Context,
) : WorkerRepository {
    private val workerManage = WorkManager.getInstance(context)
    private val constrain = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)

    override fun startDownloadImage() {
        val worker = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(constrain.build())
        workerManage.enqueue(worker.build())
    }
}