package com.example.domain.interfaces

import com.example.domain.entities.FlickrPhotosList
import com.example.domain.entities.FlickrResult

interface FlickrRepository {
    suspend fun getRecentPhotos(page: Int): FlickrResult<FlickrPhotosList?>
    suspend fun searchPhotos(term: String, page: Int): FlickrResult<FlickrPhotosList?>
}