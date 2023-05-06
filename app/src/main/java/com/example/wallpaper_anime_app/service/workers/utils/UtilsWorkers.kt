package com.example.wallpaper_anime_app.service.workers.utils

import android.Manifest
import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.wallpaper_anime_app.R
import dagger.hilt.android.flags.FragmentGetContextFix.DisableFragmentGetContextFix
import okio.Path.Companion.toPath
import java.io.File

var progressDownload = 0

fun makeStatusNotification(
    title: String,
    content: String,
    context: Context,
) {
    val CHANNEL_ID = "CHANEL_ID"
    val ID = 1

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setPriority(NotificationManager.IMPORTANCE_HIGH)
        .setProgress(100, progressDownload, false)
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        Toast.makeText(context, "u not have permission", Toast.LENGTH_SHORT).show()
        return
    }
}

//Not fix
fun saveToStorage(
    url: String = "https://nekos.best/api/v2/neko/374b7e78-62fa-4c34-a4f0-2ffddf3f8ef8.png",
    context: Context,
) {
    Log.d("SSS", File(url.toPath().name).name)
    val request = DownloadManager.Request(Uri.parse(url))
        .setTitle("Downloading..")
        .setDescription("downloading image: $url ")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            File(url.toPath().name).name
        )
        .setAllowedOverMetered(true)
        .setAllowedOverRoaming(true)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val downloadId = downloadManager.enqueue(request)

    val query = DownloadManager.Query().setFilterById(downloadId)
    val cursor = downloadManager.query(query)
    if (cursor.moveToFirst()) {
        val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
        if (columnIndex > 0) {
            val status = cursor.getInt(columnIndex)
            Log.d("status: ", "" + status)
            when(status){
                DownloadManager.STATUS_SUCCESSFUL ->
                    Log.d("STATUS_SUCCESSFUL: ", "" + status)
                DownloadManager.STATUS_FAILED ->
                    Log.d("STATUS_FAILED: ", "" + status)
                DownloadManager.STATUS_RUNNING ->
                    Log.d("STATUS_RUNNING: ", "" + status)
            }
        }
        val uri = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
        Log.d("uri: ", "" + uri)
    }
}