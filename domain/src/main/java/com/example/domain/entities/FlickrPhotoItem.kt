package com.example.domain.entities

data class FlickrPhotoItem(
    val id: String?,
    val owner: String?,
    val secret: String?,
    val server: String?,
    val farm: Int?,
    val title: String?,
    val ispublic: Boolean,
    val isfriend: Boolean,
    val isfamily: Boolean,
)
