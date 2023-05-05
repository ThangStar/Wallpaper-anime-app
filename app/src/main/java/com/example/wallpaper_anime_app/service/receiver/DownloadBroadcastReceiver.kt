package com.example.wallpaper_anime_app.service.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.wallpaper_anime_app.service.workers.utils.makeStatusNotification

class DownloadBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val action = p1!!.action
        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE == action){
        }
    }
}