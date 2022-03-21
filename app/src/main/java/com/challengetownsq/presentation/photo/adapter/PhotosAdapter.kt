package com.challengetownsq.presentation.photo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.challengetownsq.R
import com.challengetownsq.databinding.ItemPhotoBinding
import com.challengetownsq.presentation.photo.callback.PhotoClickCallback
import com.challengetownsq.presentation.photo.model.PhotoUiModel

class PhotosAdapter(
    private val photoClickCallback: PhotoClickCallback
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    private var photos = listOf<PhotoUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemView =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = photos[position]

        with(holder) {
            binding.tvTitle.text = photo.title
            binding.ivPhoto.load(photo.thumbnailUrl) {
                placeholder(R.drawable.placeholder)
            }
            itemView.setOnClickListener { photoClickCallback.clickListener(photo) }
        }
    }

    fun setPhotos(photos: List<PhotoUiModel>){
        this.photos = photos
    }

    override fun getItemCount() = photos.size

    inner class PhotosViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)
}