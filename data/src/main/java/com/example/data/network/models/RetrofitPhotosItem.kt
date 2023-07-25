package com.example.data.network.models

data class RetrofitPhotosItem(
    @field:com.google.gson.annotations.SerializedName("page") val page: Int?,
    @field:com.google.gson.annotations.SerializedName("pages") val pages: Int?,
    @field:com.google.gson.annotations.SerializedName("perpage") val perpage: Int?,
    @field:com.google.gson.annotations.SerializedName("total") val total: Int?,
    @field:com.google.gson.annotations.SerializedName("photo") val photo: List<RetrofitPhotoItem>?,
    )
