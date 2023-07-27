package com.example.data.network.models

data class RetrofitPhotoItem(
    @field:com.google.gson.annotations.SerializedName("id") val id: String?,
    @field:com.google.gson.annotations.SerializedName("owner") val owner: String?,
    @field:com.google.gson.annotations.SerializedName("secret") val secret: String?,
    @field:com.google.gson.annotations.SerializedName("server") val server: String?,
    @field:com.google.gson.annotations.SerializedName("farm") val farm: Int?,
    @field:com.google.gson.annotations.SerializedName("title") val title: String?,
    @field:com.google.gson.annotations.SerializedName("ispublic") val ispublic: Int?,
    @field:com.google.gson.annotations.SerializedName("isfriend") val isfriend: Int?,
    @field:com.google.gson.annotations.SerializedName("isfamily") val isfamily: Int?,
    @field:com.google.gson.annotations.SerializedName("ownername") val ownername: String?
)
