package com.example.domain.interfaces

import com.example.domain.entities.FlickrPhotosList
import com.example.domain.entities.FlickrResult

interface FlickrRepository {
    suspend fun getRecentPhotos(): FlickrResult<FlickrPhotosList?>
    suspend fun searchPhotos(term: String): FlickrResult<FlickrPhotosList?>
}