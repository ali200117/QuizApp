package com.example.quizapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.quizapp.UI.GalleryScreen
import com.example.quizapp.app.QuizApplication


class GalleryActivity : ComponentActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val app = application as QuizApplication


        setContent {
            GalleryScreen(app.galleryStore)
        }

    }




}