package com.challengetownsq.presentation.photo.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challengetownsq.domain.PhotoUseCase
import com.challengetownsq.domain.model.Photo
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val photoUseCase: PhotoUseCase
) : ViewModel() {
    private val _photo = MutableLiveData<Photo>()
    val photo = _photo as LiveData<Photo>

    private val _photoId = MutableLiveData<Int>()
    val photoId = _photoId as LiveData<Int>

    fun getPhoto(id: Int) {
        viewModelScope.launch {
            val photo = photoUseCase.getPhoto(id)
            _photo.value = photo
        }
    }

    fun setPhotoId(id: Int) {
        _photoId.value = id

    }
}