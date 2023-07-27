package com.example.flickrdemoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.FlickrResult
import com.example.domain.usecases.FlickrUseCases
import com.example.flickrdemoapp.ui.models.FlickrState
import com.example.flickrdemoapp.ui.models.PhotoDetails
import com.example.flickrdemoapp.utils.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    fun getRecentPhotos(page: Int = 1) {
        viewModelScope.launch {
            if(page == 1)
                _flickerPhotoState.value = FlickrState.Loading
            withContext(Dispatchers.IO) {
                when(val result = flickrUseCases.getRecentPhotos(page)) {
                    is FlickrResult.OnSuccess -> {
                        result.data?.let {
                            if(it.photo?.isNotEmpty() == true) {
                                val photoResponse = Mapper.fromFlickrPhotosListToPhotosFeed(it)
                                photoResponse.searchTerm = ""
                                _flickerPhotoState.value = FlickrState.DisplayPhotos(photoResponse)
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

    fun loadMoreData(term: String, maxPages: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                IntRange(2,maxPages).map {
                    async {
                        if(term.isBlank() == true) {
                            getRecentPhotos(page = it)
                        } else {
                            searchPhotos(term = term, page = it)
                        }
                    }
                }.forEach {
                    it.await()
                }
            }
        }
    }

    fun searchPhotos(term: String, page: Int = 1) {
        viewModelScope.launch {
            if(page == 1)
                _flickerPhotoState.value = FlickrState.Loading
            withContext(Dispatchers.IO) {
                when(val result = flickrUseCases.searchPhotos(term, page)) {
                    is FlickrResult.OnSuccess -> {
                        result.data?.let {
                            if(it.photo?.isNotEmpty() == true) {
                                val photoResponse = Mapper.fromFlickrPhotosListToPhotosFeed(it)
                                photoResponse.searchTerm = term
                                _flickerPhotoState.value = FlickrState.DisplayPhotos(photoResponse)
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