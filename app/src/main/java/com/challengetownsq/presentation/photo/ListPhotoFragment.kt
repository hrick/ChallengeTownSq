package com.challengetownsq.presentation.photo

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.challengetownsq.databinding.FragmentHomePhotosBinding
import com.challengetownsq.presentation.photo.adapter.PhotosAdapter
import com.challengetownsq.presentation.photo.callback.PhotoClickCallback
import com.challengetownsq.presentation.photo.model.PhotoUiModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListPhotoFragment() : Fragment() {
    private val viewModel: PhotosViewModel by sharedViewModel()

    private lateinit var binding: FragmentHomePhotosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.photos.observe(viewLifecycleOwner, Observer { listPhotoUiModel ->
            populatePhotos(listPhotoUiModel)
        })
        viewModel.showLoading.observe(viewLifecycleOwner, Observer { show ->
            showLoading(show)
        })

        viewModel.getPhotos()
        binding.sRefreshList.setOnRefreshListener {
            if (binding.sRefreshList.isRefreshing) {
                binding.sRefreshList.isRefreshing = false
            }
            viewModel.getPhotos()
        }
        binding.rvPhotos.adapter = PhotosAdapter(photoClickCallback)
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBarHolder.visibility = View.VISIBLE
        } else {
            binding.progressBarHolder.visibility = View.GONE
        }
    }

    private fun setupRecycleView() {
        binding.rvPhotos.setHasFixedSize(true)
        val gridLayoutManager = StaggeredGridLayoutManager(2, 1)
        binding.rvPhotos.layoutManager = gridLayoutManager
    }

    private fun populatePhotos(photos: List<PhotoUiModel>) {
        val photosAdapter = binding.rvPhotos.adapter as PhotosAdapter
        photosAdapter.setPhotos(photos)
        photosAdapter.notifyDataSetChanged()
        if (photos.isEmpty()) {
            binding.rvPhotos.visibility = View.GONE
            binding.llError.visibility = View.VISIBLE
        } else {
            binding.rvPhotos.visibility = View.VISIBLE
            binding.llError.visibility = View.GONE

        }
    }

    private val photoClickCallback = PhotoClickCallback { photo ->
        val directions = ListPhotoFragmentDirections.navigateToPhotoDetail(photoId = photo.id)
        NavHostFragment.findNavController(this).navigate(directions)
    }
}