package com.challengetownsq.repository.net.api

import com.challengetownsq.repository.net.response.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("/photos")
    suspend fun getPhotosFromFavoriteAlbum(@Query("albumId") idFavAlbum: Int): Response<List<PhotoResponse>>
}