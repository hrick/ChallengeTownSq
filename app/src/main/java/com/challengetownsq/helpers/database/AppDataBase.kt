package com.challengetownsq.helpers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challengetownsq.domain.model.Photo
import com.challengetownsq.repository.data.dao.PhotoDao

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
