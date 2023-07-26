package com.example.flickrdemoapp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.entities.FlickrPhotoItem


@BindingAdapter("flickrImage")
fun ImageView.flickrImage(flickrPhoto: FlickrPhotoItem) {
    getFlickrUrl(flickrPhoto)?.let { url ->
        val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .fitCenter()

        try {
            Glide
                .with(this.context.applicationContext)
                .load(url)
                .apply(options)
                .into(this)
        } catch (e: Exception) {
            Log.e("","Thumbnail Failed in Glide...using error drawable resource")
        }
    }
}

private fun getFlickrUrl(flickrPhoto: FlickrPhotoItem): String? {
    if(flickrPhoto.server.isNullOrBlank() or
        flickrPhoto.id.isNullOrBlank() or
        flickrPhoto.secret.isNullOrBlank())
        return null

    return "https://live.staticflickr.com/"+
            flickrPhoto.server+"/"+
            flickrPhoto.id+"_"+
            flickrPhoto.secret+".jpg"
}
