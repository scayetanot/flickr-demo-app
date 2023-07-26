package com.example.flickrdemoapp.ui.models

import com.example.domain.entities.FlickrPhotoItem
import java.lang.Exception

sealed class FlickrState {
    object Empty: FlickrState()
    object Loading: FlickrState()
    data class RecentPhotos(val photos: List<FlickrPhotoItem>) : FlickrState()
    data class Error(val e: Exception) : FlickrState()

}