package com.example.wallpaper_anime_app.di

import com.example.wallpaper_anime_app.service.workers.repository.WorkerRepository
import com.example.wallpaper_anime_app.service.workers.repository.WorkerRepositoryImpl
import com.example.wallpaper_anime_app.ui.repository.APIHelper
import com.example.wallpaper_anime_app.ui.repository.APIHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: APIHelperImpl): APIHelper

    @Binds
    abstract fun bindWorkerRepository(workerRepositoryImpl: WorkerRepositoryImpl): WorkerRepository
}