package com.example.data.network

import com.example.data.network.models.RetrofitPhotosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrApiHelperImpl @Inject constructor(
    private var flickrService: FlickrService
): FlickrApiHelper {
    override suspend fun getRecentPhotos(): FlickrApiResult<RetrofitPhotosItem> =
        withContext(Dispatchers.IO) {
            try {
                val response = flickrService.getRecentPhotos()
                FlickrApiResult.OnSuccess(response.photos)
            } catch (e: Exception) {
                FlickrApiResult.OnError(e)
            }
    }

    override suspend fun searchPhotos(term: String): FlickrApiResult<RetrofitPhotosItem> =
        withContext(Dispatchers.IO) {
            try {
                val response = flickrService.seachPhotos(text = term)
                FlickrApiResult.OnSuccess(response.photos)
            } catch (e: Exception) {
                FlickrApiResult.OnError(e)
            }
    }

}