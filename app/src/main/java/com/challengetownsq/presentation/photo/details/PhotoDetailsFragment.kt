package com.challengetownsq.presentation.photo.details

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.challengetownsq.PhotosApplication
import com.challengetownsq.R
import com.challengetownsq.databinding.FragmentPhotoDetailsBinding
import com.challengetownsq.domain.model.Photo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhotoDetailsFragment() : Fragment() {

    private lateinit var binding: FragmentPhotoDetailsBinding

    private val viewModel: PhotoDetailsViewModel by sharedViewModel()
    private val args by navArgs<PhotoDetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoId = args.photoId
        viewModel.photo.observe(viewLifecycleOwner, Observer { photo ->
            populatePhoto(photo)
        })
        viewModel.getPhoto(photoId)
    }

    private fun populatePhoto(photo: Photo?) {
        binding.tvTitle.text = photo?.title
        binding.ivPhoto.load(photo?.url) {
            placeholder(R.drawable.placeholder)
        }
    }

}