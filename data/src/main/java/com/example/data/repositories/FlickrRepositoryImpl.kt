package com.example.data.repositories

import com.example.data.network.FlickrApiHelper
import com.example.data.network.FlickrApiResult
import com.example.data.utils.FlickrMapper
import com.example.domain.entities.FlickrPhotosList
import com.example.domain.entities.FlickrResult
import com.example.domain.interfaces.FlickrRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrRepositoryImpl(
    val flickrApiHelper: FlickrApiHelper
): FlickrRepository {
    override suspend fun getRecentPhotos(): FlickrResult<FlickrPhotosList?> = withContext(Dispatchers.IO) {
        when (val response = flickrApiHelper.getRecentPhotos()) {
            is FlickrApiResult.OnSuccess -> {
                FlickrResult.OnSuccess(
                    FlickrMapper.fromRetrofitToFlickrPhotosList(response.data)
                )
            }

            is FlickrApiResult.OnError -> {
                FlickrResult.OnError(
                    response.exception
                )
            }
        }
    }



    override suspend fun searchPhotos(term: String): FlickrResult<FlickrPhotosList?> = withContext(Dispatchers.IO) {
        when (val response = flickrApiHelper.searchPhotos(term)) {
            is FlickrApiResult.OnSuccess -> {
                FlickrResult.OnSuccess(
                    FlickrMapper.fromRetrofitToFlickrPhotosList(response.data)
                )
            }

            is FlickrApiResult.OnError -> {
                FlickrResult.OnError(
                    response.exception
                )
            }
        }
    }


}