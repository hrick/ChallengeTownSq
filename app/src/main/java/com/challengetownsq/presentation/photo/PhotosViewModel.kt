package com.challengetownsq.presentation.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challengetownsq.domain.PhotoUseCase
import com.challengetownsq.presentation.photo.model.PhotoUiModel
import com.challengetownsq.presentation.photo.model.toUiModel
import kotlinx.coroutines.launch

class PhotosViewModel (
    private val photoUseCase: PhotoUseCase
) : ViewModel() {
    private val idAlbum = 1
    private val _photos = MutableLiveData<List<PhotoUiModel>>()
    val photos = _photos as LiveData<List<PhotoUiModel>>

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading = _showLoading as LiveData<Boolean>

    fun getPhotos() {
        viewModelScope.launch {
            _showLoading.value = true
            val photoList = photoUseCase.getPhotosFromAlbum(idAlbum)

            _photos.value = photoList.map { photo ->
                photo.toUiModel()
            }

            _showLoading.value = false
        }
    }

}