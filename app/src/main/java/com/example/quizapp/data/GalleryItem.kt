package com.example.quizapp.data

import android.net.Uri

data class GalleryItem(

    val name: String,

    //Hardkodet bilder
    val imageResId: Int ? = null,

    //bilder fra cameraroll
    val imageUri: Uri? = null
    )
