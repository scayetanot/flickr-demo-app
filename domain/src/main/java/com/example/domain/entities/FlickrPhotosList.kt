package com.example.domain.entities

data class FlickrPhotosList(
    val page: Int?,
    val pages: Int?,
    val perpage: Int?,
    val total: Int?,
    val photo: List<FlickrPhotoItem>?,
)
