package com.example.wallpaper_anime_app.service.workers.repository

interface WorkerRepository {
    fun startDownloadImage(url: String)
}