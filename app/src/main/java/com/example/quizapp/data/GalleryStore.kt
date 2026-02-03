package com.example.quizapp.data

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class GalleryStore {


//Boks som holder listen av items definert i galleryitem klassen

    val items = mutableStateListOf<GalleryItem>()

    var newImageUri by mutableStateOf<Uri?>(null)
        private set

    //fra A til Z
    fun sortAZ(){
        items.sortBy { it.name }
    }

    //fra Z til A
    fun sortZA(){

        items.sortByDescending { it.name }
    }


    //DELETE BUTTON
    fun remove(item : GalleryItem) {
        items.remove(item)
    }


    //ADD Button -> image fra gallery
    fun addFromGallery (uri : Uri) {
        newImageUri = uri
    }

    fun dismissImage () {
         newImageUri = null
    }

    fun addTextImage(answer : String) {
        items.add(GalleryItem(name = answer, imageUri = newImageUri))
        newImageUri = null
    }
}