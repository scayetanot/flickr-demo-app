package com.example.flickrdemoapp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.entities.FlickrPhotoItem
import com.example.flickrdemoapp.ui.models.PhotoDetails


@BindingAdapter("flickrImage")
fun ImageView.flickrImage(flickrPhoto: PhotoDetails) {
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

private fun getFlickrUrl(flickrPhoto: PhotoDetails): String? {
    if(flickrPhoto.server.isBlank() or
        flickrPhoto.id.isBlank() or
        flickrPhoto.secret.isBlank())
        return null

    return "https://live.staticflickr.com/"+
            flickrPhoto.server+"/"+
            flickrPhoto.id+"_"+
            flickrPhoto.secret+".jpg"
}
