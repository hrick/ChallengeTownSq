package com.challengetownsq

import android.app.Application
import androidx.room.Room
import com.challengetownsq.di.photoServiceModule
import com.challengetownsq.helpers.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotosApplication  : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@PhotosApplication)
            modules(listOf(photoServiceModule))
        }
    }
}