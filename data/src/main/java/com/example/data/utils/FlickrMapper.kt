package com.example.data.utils

import com.example.data.network.models.RetrofitPhotoItem
import com.example.data.network.models.RetrofitPhotosItem
import com.example.domain.entities.FlickrPhotoItem
import com.example.domain.entities.FlickrPhotosList

object FlickrMapper {
    fun fromRetrofitToFlickrPhotosList(item: RetrofitPhotosItem): FlickrPhotosList? {
        return FlickrPhotosList(
            page = item.page,
            pages = item.pages,
            perpage = item.perpage,
            total = item.total,
            photo = fromRetrofitToListOfFlickrPhoto(item.photo)
        )
    }

    fun fromRetrofitToListOfFlickrPhoto(items: List<RetrofitPhotoItem?>): List<FlickrPhotoItem> {
        val listOfPhotos: MutableList<FlickrPhotoItem> = mutableListOf()
        items.forEach {
            listOfPhotos.add(
                FlickrPhotoItem(
                    id = it?.id,
                    owner = it?.ownername,
                    secret = it?.secret,
                    server = it?.server,
                    farm = it?.farm,
                    title = it?.title,
                    ispublic = it?.ispublic != 0,
                    isfamily = it?.isfamily != 0,
                    isfriend = it?.isfriend != 0
                )
            )
        }
        return listOfPhotos.toList()
    }
}