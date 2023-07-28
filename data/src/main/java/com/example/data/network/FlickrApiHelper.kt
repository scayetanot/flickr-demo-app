package com.example.data.network

import com.example.data.network.models.RetrofitPhotosItem
import com.example.data.network.models.RetrofitPhotosResponse

interface FlickrApiHelper {
    suspend fun getRecentPhotos(page: Int): FlickrApiResult<RetrofitPhotosItem>
    suspend fun searchPhotos(term: String, page: Int): FlickrApiResult<RetrofitPhotosItem>
}