package com.example.flickrdemoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flickrdemoapp.databinding.FragmentDetailsBinding
import com.example.flickrdemoapp.ui.models.FlickrState
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    private val viewModel: FlickrViewModel by viewModels()

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater)
        binding?.item = args.currentPhoto
        binding?.viewModel = viewModel
        binding?.executePendingBindings()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        lifecycleScope.launchWhenCreated {
            viewModel.flickerPhotoState.collect {
                when (it) {
                    FlickrState.CloseDetails -> {
                        findNavController().navigateUp()
                    }
                    else -> {}
                }
            }
        }
    }

}