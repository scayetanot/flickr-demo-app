package com.example.flickrdemoapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoDetails(
    val id: String,
    val secret: String,
    val server: String,
    val title: String,
    val owner: String,
 ) : Parcelable
