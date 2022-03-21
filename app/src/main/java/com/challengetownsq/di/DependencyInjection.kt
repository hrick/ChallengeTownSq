package com.challengetownsq.di

import android.app.Application
import androidx.room.Room
import com.challengetownsq.domain.PhotoUseCase
import com.challengetownsq.domain.PhotoUseCaseImpl
import com.challengetownsq.helpers.database.AppDatabase
import com.challengetownsq.helpers.network.Service
import com.challengetownsq.presentation.photo.PhotosViewModel
import com.challengetownsq.presentation.photo.details.PhotoDetailsViewModel
import com.challengetownsq.repository.data.PhotoRepository
import com.challengetownsq.repository.data.PhotoRepositoryImpl
import com.challengetownsq.repository.data.dao.PhotoDao
import com.challengetownsq.repository.net.PhotoRepositoryApi
import com.challengetownsq.repository.net.PhotoRepositoryApiImpl
import com.challengetownsq.repository.net.api.PhotoApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val photoServiceModule = module {

    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "favorite-photos-db")
            .build()
    }

    fun provideDaoPhoto(dataBase: AppDatabase): PhotoDao {
        return dataBase.photoDao()
    }

    single { provideDataBase(androidApplication()) }

    single { provideDaoPhoto(get()) }

    single { Service().createService(PhotoApi::class.java) }

    single<PhotoRepository> { PhotoRepositoryImpl(get()) }

    single<PhotoRepositoryApi> { PhotoRepositoryApiImpl(get()) }

    single<PhotoUseCase> { PhotoUseCaseImpl(get(), get()) }

    single { PhotosViewModel(get()) }

    single { PhotoDetailsViewModel(get()) }
}