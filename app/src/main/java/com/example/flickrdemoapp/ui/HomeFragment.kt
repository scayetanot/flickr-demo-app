package com.example.flickrdemoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrdemoapp.R
import com.example.flickrdemoapp.databinding.FragmentHomeBinding
import com.example.flickrdemoapp.ui.adapters.FlickrPhotosAdapters
import com.example.flickrdemoapp.ui.models.FlickrState
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel
import com.example.flickrdemoapp.utils.Mapper.fromListFlickrPhotoItemToListPhotoDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel: FlickrViewModel by viewModels()

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

        binding?.searchBar

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
                     }
                     is FlickrState.ShowPhotoDetails -> {
                         viewModel.resetState()
                         val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.photo )
                         findNavController().navigate(action)
                     }
                     is FlickrState.RecentPhotos -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                         binding?.photosGrid?.visibility = View.VISIBLE
                         binding?.errorLayout?.visibility = View.GONE
                         flickrPhotosAdapter.submitList(it.photos)
                     }
                     is FlickrState.Error -> {
                         binding?.loading?.loadingLayout?.visibility = View.GONE
                         binding?.photosGrid?.visibility = View.GONE
                         binding?.errorLayout?.visibility = View.VISIBLE
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