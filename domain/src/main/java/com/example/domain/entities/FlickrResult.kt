package com.example.domain.entities

sealed class FlickrResult<out T> {
    data class OnSuccess<T>(val data: T) : FlickrResult<T>()
    data class OnError(val exception: Exception) : FlickrResult<Nothing>()
}