package com.example.domain.interfaces

import com.example.domain.entities.FlickrResult

interface FlickrRepository {
    suspend fun getRecentPhotos(): FlickrResult<>
    suspend fun searchPhotos(term: String): FlickrResult<>
}