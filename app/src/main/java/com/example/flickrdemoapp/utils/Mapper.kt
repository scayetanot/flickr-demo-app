package com.example.flickrdemoapp.utils

import com.example.domain.entities.FlickrPhotoItem
import com.example.flickrdemoapp.ui.models.PhotoDetails

object Mapper {
    fun fromListFlickrPhotoItemToListPhotoDetails(listOfPhotos: List<FlickrPhotoItem>): List<PhotoDetails> {
        val listOfPhotoDetails: MutableList<PhotoDetails> = mutableListOf()
        listOfPhotos.forEach {
            listOfPhotoDetails.add(fromFlickrPhotoItemToPhotoDetails(it))
        }
        return listOfPhotoDetails.toList()
    }

    fun fromFlickrPhotoItemToPhotoDetails(flickr: FlickrPhotoItem): PhotoDetails {
        return PhotoDetails(
            id = flickr.id ?: "",
            secret = flickr.secret ?: "",
            server = flickr.server ?: "",
            title = flickr.title ?: "",
            owner = flickr.owner ?: "",
        )
    }
}