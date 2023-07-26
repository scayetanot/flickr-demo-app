package com.example.flickrdemoapp.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entities.FlickrPhotoItem
import com.example.flickrdemoapp.ui.viewholders.FlickrPhotosViewHolder
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel

class FlickrPhotosAdapters(private val context: Context?, private val viewModel: FlickrViewModel) :
    ListAdapter<FlickrPhotoItem, FlickrPhotosViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPhotosViewHolder =
        FlickrPhotosViewHolder.from(parent)

    override fun onBindViewHolder(holder: FlickrPhotosViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it, viewModel)
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<FlickrPhotoItem>() {
            override fun areItemsTheSame(
                oldItem: FlickrPhotoItem,
                newItem: FlickrPhotoItem
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: FlickrPhotoItem,
                newItem: FlickrPhotoItem
            ): Boolean =
                oldItem == newItem
        }
    }
}