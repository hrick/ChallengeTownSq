package com.challengetownsq.repository.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challengetownsq.domain.model.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo") suspend fun getAllPhotos(): List<Photo>

    @Query("SELECT * FROM photo where album_id = :albumId") suspend fun getPhotosFromAlbum(albumId: Int): List<Photo>?

    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertPhoto(vararg photos: Photo)

    @Query("SELECT * FROM photo WHERE id == :idPhoto LIMIT 1")
    suspend fun getPhoto(idPhoto: Int): Photo

}