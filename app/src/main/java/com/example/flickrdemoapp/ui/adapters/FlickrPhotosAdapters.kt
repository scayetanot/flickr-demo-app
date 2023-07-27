package com.example.flickrdemoapp.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entities.FlickrPhotoItem
import com.example.flickrdemoapp.ui.models.PhotoDetails
import com.example.flickrdemoapp.ui.viewholders.FlickrPhotosViewHolder
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel

class FlickrPhotosAdapters(private val viewModel: FlickrViewModel) :
    ListAdapter<PhotoDetails, FlickrPhotosViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPhotosViewHolder =
        FlickrPhotosViewHolder.from(parent)

    override fun onBindViewHolder(holder: FlickrPhotosViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it, viewModel)
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoDetails>() {
            override fun areItemsTheSame(
                oldItem: PhotoDetails,
                newItem: PhotoDetails
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: PhotoDetails,
                newItem: PhotoDetails
            ): Boolean =
                oldItem == newItem
        }
    }
}