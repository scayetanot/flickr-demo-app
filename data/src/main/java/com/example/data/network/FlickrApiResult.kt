package com.example.data.network

sealed class FlickrApiResult<out T> {
    data class OnSuccess<T>(val data: T) : FlickrApiResult<T>()
    data class OnError(val exception: Exception) : FlickrApiResult<Nothing>()
}
