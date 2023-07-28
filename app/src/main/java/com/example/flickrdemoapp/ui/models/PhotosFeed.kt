package com.example.flickrdemoapp.ui.models

data class PhotosFeed(
    var currentPage: Int,
    var totalPage: Int,
    var searchTerm: String,
    var photos: List<PhotoDetails>?
)
