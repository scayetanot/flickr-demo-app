package com.example.flickrdemoapp.utils

import com.example.domain.entities.FlickrPhotoItem
import com.example.domain.entities.FlickrPhotosList
import com.example.flickrdemoapp.ui.models.PhotoDetails
import com.example.flickrdemoapp.ui.models.PhotosFeed

object Mapper {

    fun fromFlickrPhotosListToPhotosFeed(item: FlickrPhotosList): PhotosFeed {
        return PhotosFeed(
            currentPage = item.page ?:1,
            totalPage = item.pages ?:1,
            photos = fromListFlickrPhotoItemToListPhotoDetails(item.photo)
        )

    }
    fun fromListFlickrPhotoItemToListPhotoDetails(listOfPhotos: List<FlickrPhotoItem>?): List<PhotoDetails> {
        val listOfPhotoDetails: MutableList<PhotoDetails> = mutableListOf()
        listOfPhotos?.forEach {
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