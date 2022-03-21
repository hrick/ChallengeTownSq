package com.challengetownsq.repository.net

import com.challengetownsq.domain.model.Photo
import com.challengetownsq.helpers.network.Output
import com.challengetownsq.helpers.network.exceptions.GetPhotosException
import com.challengetownsq.helpers.network.parseResponse
import com.challengetownsq.repository.net.api.PhotoApi
import com.challengetownsq.repository.net.response.toPhoto

class PhotoRepositoryApiImpl(
    private val service: PhotoApi
): PhotoRepositoryApi {
    override suspend fun getPhotosFromFavoriteAlbum(favAlbumId: Int): List<Photo> {

        return when (val result = service.getPhotosFromFavoriteAlbum(favAlbumId).parseResponse()) {
            is Output.Success -> {
                val photosResponseList = result.value

                photosResponseList.map {
                    it.toPhoto()
                }
            }
            is Output.Failure -> throw GetPhotosException()
        }
    }
}

interface PhotoRepositoryApi {
    suspend fun getPhotosFromFavoriteAlbum(favAlbumId: Int): List<Photo>
}
