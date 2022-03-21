package com.challengetownsq.repository.net.response

import com.challengetownsq.domain.model.Photo

data class PhotoResponse(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
fun PhotoResponse.toPhoto() = Photo(
    id = this.id,
    albumId = this.albumId,
    title = this.title,
    url = this.url,
    thumbnailUrl = this.thumbnailUrl,
)