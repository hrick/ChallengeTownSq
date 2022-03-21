package com.challengetownsq.repository.data

import com.challengetownsq.domain.model.Photo
import com.challengetownsq.repository.data.dao.PhotoDao

class PhotoRepositoryImpl(
    private val dao: PhotoDao
) : PhotoRepository {
    override suspend fun getPhotosFromFavAlbum(idAlbum: Int): List<Photo> {
        val photos = dao.getPhotosFromAlbum(idAlbum)
        return photos ?: listOf()
    }

    override suspend fun getPhoto(idPhoto: Int): Photo {
        return dao.getPhoto(idPhoto)
    }

    override suspend fun saveAllPhotos(photos: List<Photo>) {
        photos.forEach { photo ->
            dao.insertPhoto(photo)
        }
    }

}

interface PhotoRepository {
    suspend fun getPhotosFromFavAlbum(idAlbum: Int): List<Photo>
    suspend fun saveAllPhotos(photos: List<Photo>)
    suspend fun getPhoto(idPhoto: Int): Photo
}