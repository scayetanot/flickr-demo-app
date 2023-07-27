package com.example.flickrdemoapp.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickrdemoapp.databinding.FragmentHomeBinding
import com.example.flickrdemoapp.ui.adapters.FlickrPhotosAdapters
import com.example.flickrdemoapp.ui.models.FlickrState
import com.example.flickrdemoapp.ui.models.PhotoDetails
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel: FlickrViewModel by viewModels()

    private var listOfPhotosToDisplay: MutableList<PhotoDetails> = mutableListOf()

    private val flickrPhotosAdapter : FlickrPhotosAdapters by lazy {
        FlickrPhotosAdapters(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding?.viewModel = viewModel
        binding?.photosGrid?.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = flickrPhotosAdapter
        }

        binding?.searchBar?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if(binding?.searchBar?.text.isNullOrEmpty()) {
                    viewModel.getRecentPhotos()
                } else {
                    viewModel.searchPhotos(binding?.searchBar?.text.toString())
                }
                binding?.searchBar?.text?.clear()
                return@OnKeyListener true
            }
            false
        })

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
                         binding?.photosGrid?.visibility = View.GONE
                         binding?.errorLayout?.visibility = View.GONE
                         binding?.noImagesFoundLayout?.visibility = View.GONE
                     }
                     FlickrState.NoPhotos -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                         binding?.photosGrid?.visibility = View.GONE
                         binding?.errorLayout?.visibility = View.GONE
                         binding?.noImagesFoundLayout?.visibility = View.VISIBLE
                     }
                     is FlickrState.ShowPhotoDetails -> {
                         viewModel.resetState()
                         val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.photo )
                         findNavController().navigate(action)
                     }
                     is FlickrState.DisplayPhotos -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                         binding?.photosGrid?.visibility = View.VISIBLE
                         binding?.errorLayout?.visibility = View.GONE
                         binding?.noImagesFoundLayout?.visibility = View.GONE

                         it.photos?.let {
                             listOfPhotosToDisplay = it.toMutableList()
                             flickrPhotosAdapter.submitList(it)
                         }
                     }
                     is FlickrState.Error -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                         binding?.photosGrid?.visibility = View.GONE
                         binding?.errorLayout?.visibility = View.VISIBLE
                         binding?.noImagesFoundLayout?.visibility = View.GONE
                         displayErrorMessage(it.e.message)
                     }
                     else -> Unit
                 }
             }
         }
     }

    private fun displayErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }


}