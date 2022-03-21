package com.challengetownsq.presentation.photo.model

import com.challengetownsq.domain.model.Photo

class PhotoUiModel (
    val title: String,
    val id: Int,
    val thumbnailUrl: String,
)

fun Photo.toUiModel() = PhotoUiModel(
    title = this.title,
    thumbnailUrl = this.thumbnailUrl,
    id = this.id,
)