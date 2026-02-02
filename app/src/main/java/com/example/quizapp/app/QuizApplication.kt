package com.example.quizapp.app

import android.app.Application
import com.example.quizapp.R
import com.example.quizapp.data.GalleryItem
import com.example.quizapp.data.GalleryStore

class QuizApplication : Application(){

    lateinit var galleryStore: GalleryStore
        private set

    override fun onCreate(){
        super.onCreate()

        galleryStore = GalleryStore()

        galleryStore.items.add(GalleryItem("WOOF WOOF", R.drawable.hund))
        galleryStore.items.add(GalleryItem("katt", R.drawable.katt))
        galleryStore.items.add(GalleryItem("mario", R.drawable.mario))

    }


}