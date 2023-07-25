package com.example.data.network

import com.example.data.network.models.RetrofitPhotosResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface FlickrService {
    @POST("?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    suspend fun getRecentPhotos(
        @Query("api_key") api_key: String
    ): RetrofitPhotosResponse

    @POST("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun seachPhotos(
        @Query("api_key") api_key: String,
        @Query("text") text: String): RetrofitPhotosResponse
}