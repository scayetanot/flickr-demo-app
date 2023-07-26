package com.example.data.network.models

data class RetrofitPhotosResponse(
    @field:com.google.gson.annotations.SerializedName("photos") val photos: RetrofitPhotosItem,
    @field:com.google.gson.annotations.SerializedName("stat") val stat: String?,
    )