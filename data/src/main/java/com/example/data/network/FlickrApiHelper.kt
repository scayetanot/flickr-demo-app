package com.example.data.network

import com.example.data.network.models.RetrofitPhotosItem
import com.example.data.network.models.RetrofitPhotosResponse

interface FlickrApiHelper {
    suspend fun getRecentPhotos(): FlickrApiResult<RetrofitPhotosItem?>
    suspend fun searchPhotos(term: String): FlickrApiResult<RetrofitPhotosItem?>
}