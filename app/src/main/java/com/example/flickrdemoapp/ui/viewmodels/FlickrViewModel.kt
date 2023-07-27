package com.example.flickrdemoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.FlickrPhotoItem
import com.example.domain.entities.FlickrResult
import com.example.domain.usecases.FlickrUseCases
import com.example.flickrdemoapp.ui.models.FlickrState
import com.example.flickrdemoapp.ui.models.PhotoDetails
import com.example.flickrdemoapp.utils.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
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
                        result.data?.photo?.let {
                            if(it.isNotEmpty()) {
                                _flickerPhotoState.value = FlickrState.DisplayPhotos(
                                    Mapper.fromListFlickrPhotoItemToListPhotoDetails(it)
                                )
                            } else {
                                _flickerPhotoState.value = FlickrState.DisplayPhotos(
                                    Mapper.fromListFlickrPhotoItemToListPhotoDetails(it)
                                )
                            }
                            return@withContext
                        }
                        _flickerPhotoState.value = FlickrState.NoPhotos
                    }

                    is FlickrResult.OnError -> {
                        _flickerPhotoState.value = FlickrState.Error(result.exception)
                    }
                }
            }
        }
    }

    fun searchPhotos(term: String) {
        viewModelScope.launch {
            _flickerPhotoState.value = FlickrState.Loading
            withContext(Dispatchers.IO) {
                when(val result = flickrUseCases.searchPhotos(term)) {
                    is FlickrResult.OnSuccess -> {
                        result.data?.photo?.let {
                            if(it.isNotEmpty()) {
                                _flickerPhotoState.value = FlickrState.DisplayPhotos(
                                    Mapper.fromListFlickrPhotoItemToListPhotoDetails(it)
                                )
                            } else {
                                _flickerPhotoState.value = FlickrState.NoPhotos
                            }
                            return@withContext
                        }
                        _flickerPhotoState.value = FlickrState.NoPhotos
                    }

                    is FlickrResult.OnError -> {
                        _flickerPhotoState.value = FlickrState.Error(result.exception)
                    }
                }
            }
        }
    }

    fun onPhotoClick(photo: PhotoDetails) {
        _flickerPhotoState.value = FlickrState.ShowPhotoDetails(photo)
    }
    fun onRetry() {
        _flickerPhotoState.value = FlickrState.Loading
        getRecentPhotos()
    }

    fun onClose() {
        _flickerPhotoState.value = FlickrState.CloseDetails
    }

    fun resetState() {
        _flickerPhotoState.value = FlickrState.Empty
    }
}