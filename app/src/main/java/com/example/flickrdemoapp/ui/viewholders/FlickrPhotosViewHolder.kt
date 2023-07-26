package com.example.flickrdemoapp.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.FlickrPhotoItem
import com.example.flickrdemoapp.databinding.ItemPhotoGridBinding
import com.example.flickrdemoapp.ui.viewmodels.FlickrViewModel

class FlickrPhotosViewHolder(val binding: ItemPhotoGridBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FlickrPhotoItem, viewModel: FlickrViewModel) {
        binding.item = item
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FlickrPhotosViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPhotoGridBinding.inflate(layoutInflater, parent, false)
            return FlickrPhotosViewHolder(binding)
        }
    }
}