package com.example.data.network

import com.example.data.BuildConfig
import com.example.data.network.models.RetrofitPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {
    @GET("services/rest")
    suspend fun getRecentPhotos(
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("api_key") api_key: String = BuildConfig.FLICKR_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
       // @Query("extras") extras: String = "url_s,date_taken,owner_name",
        @Query("page") page:Int = 1
    ): RetrofitPhotosResponse

    @GET("services/rest")
    suspend fun seachPhotos(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") api_key: String = BuildConfig.FLICKR_API_KEY,
        @Query("text") text: String="",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): RetrofitPhotosResponse
}