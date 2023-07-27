package com.example.flickrdemoapp.ui.models

import com.example.domain.entities.FlickrPhotoItem
import java.lang.Exception

sealed class FlickrState {
    object Empty: FlickrState()
    object Loading: FlickrState()
    object CloseDetails: FlickrState()
    data class ShowPhotoDetails(val photo: PhotoDetails) : FlickrState()
    data class RecentPhotos(val photos: List<PhotoDetails>?) : FlickrState()
    data class Error(val e: Exception) : FlickrState()

}