package com.challengetownsq.presentation.photo.callback

import com.challengetownsq.presentation.photo.model.PhotoUiModel

class PhotoClickCallback(val clickListener: (photo: PhotoUiModel) -> Unit) {
    fun onClick(photo: PhotoUiModel) = clickListener(photo)
}