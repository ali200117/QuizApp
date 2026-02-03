package com.example.quizapp.app

import android.app.Application
import com.example.quizapp.R
import com.example.quizapp.data.GalleryItem
import com.example.quizapp.data.GalleryStore
import com.example.quizapp.data.QuizStore

class QuizApplication : Application(){

    lateinit var galleryStore: GalleryStore
        private set
    lateinit var quizStore: QuizStore
        private set

    override fun onCreate(){
        super.onCreate()

        galleryStore = GalleryStore()
        quizStore = QuizStore()

        galleryStore.items.add(GalleryItem("hund", R.drawable.hund))
        galleryStore.items.add(GalleryItem("katt", R.drawable.katt))
        galleryStore.items.add(GalleryItem("mario", R.drawable.mario))

    }


}