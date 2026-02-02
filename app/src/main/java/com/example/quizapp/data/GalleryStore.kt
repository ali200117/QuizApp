package com.example.quizapp.data

import androidx.compose.runtime.mutableStateListOf

class GalleryStore {


//Boks som holder listen av items definert i galleryitem klassen

    val items = mutableStateListOf<GalleryItem>()

    //fra A til Z
    fun sortAZ(){
        items.sortBy { it.name }
    }

    //fra Z til A
    fun sortZA(){

        items.sortByDescending { it.name }
    }





}