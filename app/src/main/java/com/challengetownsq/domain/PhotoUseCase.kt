package com.challengetownsq.domain

import android.util.Log
import com.challengetownsq.domain.model.Photo
import com.challengetownsq.helpers.database.AppDatabase
import com.challengetownsq.repository.data.PhotoRepository
import com.challengetownsq.repository.net.PhotoRepositoryApi
import java.lang.Exception

class PhotoUseCaseImpl(
    private val photoRepositoryApi: PhotoRepositoryApi,
    private val photoRepository: PhotoRepository,
) : PhotoUseCase {

    override suspend fun getPhotosFromAlbum(idAlbum: Int): List<Photo> {
        try {
            val photosFromService = photoRepositoryApi.getPhotosFromFavoriteAlbum(idAlbum)
            photoRepository.saveAllPhotos(photosFromService)
            return photosFromService
        } catch (ex: Exception) {
            val photos = photoRepository.getPhotosFromFavAlbum(idAlbum)
            if (photos.isEmpty()) {
                return listOf()
            }
                return photoRepository.getPhotosFromFavAlbum(idAlbum)
            }
        }

    override suspend fun getPhoto(idPhoto: Int): Photo {
        return photoRepository.getPhoto(idPhoto)
    }


}

    interface PhotoUseCase {
        suspend fun getPhotosFromAlbum(idAlbum: Int): List<Photo>
        suspend fun getPhoto(idPhoto: Int): Photo
    }