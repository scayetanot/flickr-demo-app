package com.example.flickrdemoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrdemoapp.databinding.FragmentHomeBinding
import com.example.flickrdemoapp.ui.adapters.FlickrPhotosAdapters
import com.example.flickrdemoapp.ui.models.FlickrState
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel: FlickrViewModel by viewModels()

    private val flickrPhotosAdapter : FlickrPhotosAdapters by lazy {
        FlickrPhotosAdapters(requireContext(), viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding?.photosGrid?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = flickrPhotosAdapter
        }
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        subscribeToPhotos()
    }

     private fun subscribeToPhotos() {
         lifecycleScope.launchWhenCreated {
             viewModel.flickerPhotoState.collect {
                 when (it) {
                     FlickrState.Loading -> {
                         binding?.loading?.loadingLayout?.visibility = View.VISIBLE
                     }
                     is FlickrState.RecentPhotos -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                         flickrPhotosAdapter.submitList(it.photos)
                     }
                     is FlickrState.Error -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                     }
                     else -> Unit
                 }
             }
         }
     }


}