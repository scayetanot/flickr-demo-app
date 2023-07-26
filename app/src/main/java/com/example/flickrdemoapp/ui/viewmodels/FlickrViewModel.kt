package com.example.flickrdemoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.FlickrResult
import com.example.domain.usecases.FlickrUseCases
import com.example.flickrdemoapp.ui.models.FlickrState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlickrViewModel @Inject constructor (
    private val flickrUseCases: FlickrUseCases
        ): ViewModel(){


    private val _flickerPhotoState = MutableStateFlow<FlickrState>(FlickrState.Empty)
    val flickerPhotoState: StateFlow<FlickrState> = _flickerPhotoState

    init{
        getRecentPhotos()
    }

    fun getRecentPhotos() {
        viewModelScope.launch {
            _flickerPhotoState.value = FlickrState.Loading
            withContext(Dispatchers.IO) {
                when(val result = flickrUseCases.getRecentPhotos()) {
                    is FlickrResult.OnSuccess -> {

                    }

                    is FlickrResult.OnError -> {
                        _flickerPhotoState.value = FlickrState.Error(result.exception)
                    }
                }
            }
        }
    }


}