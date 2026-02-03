package com.example.quizapp.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.quizapp.UI.quizScreen
import com.example.quizapp.app.QuizApplication


class QuizActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as QuizApplication
        val quizStore = app.quizStore
        quizStore.start(app.galleryStore.items)

        setContent {
            quizScreen(store = quizStore)
        }
    }

}