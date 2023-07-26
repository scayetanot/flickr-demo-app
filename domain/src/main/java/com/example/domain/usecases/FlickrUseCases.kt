package com.example.domain.usecases

import com.example.domain.interfaces.FlickrRepository

class FlickrUseCases(
    private val flickerRepository: FlickrRepository
) {
    suspend fun getRecentPhotos() =
        flickerRepository.getRecentPhotos()

    suspend fun searchPhotos(term: String) =
        flickerRepository.searchPhotos(term)
}