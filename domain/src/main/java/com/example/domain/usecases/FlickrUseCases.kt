package com.example.domain.usecases

import com.example.domain.interfaces.FlickrRepository

class FlickrUseCases(
    private val flickerRepository: FlickrRepository
) {
    suspend fun getRecentPhotos(page: Int = 1) =
        flickerRepository.getRecentPhotos(page = page)

    suspend fun searchPhotos(term: String, page: Int) =
        flickerRepository.searchPhotos(term, page)
}