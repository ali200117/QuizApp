package com.example.quizapp.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.quizapp.data.QuizStore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun quizScreen (store : QuizStore) {

    val q = store.question?: return


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("QUIZ") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    label = { Text("items") },
                    icon = {}
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(text = "your score: ${store.scoreCounter}")

            when {
                q.correctItem.imageUri != null -> {
                    AsyncImage(
                        model = q.correctItem.imageUri,
                        contentDescription = q.correctItem.name,
                        modifier = Modifier.size(220.dp)
                    )
                }

                q.correctItem.imageResId != null -> {
                    Image(
                        painter = painterResource(id = q.correctItem.imageResId),
                        contentDescription = q.correctItem.name,
                        modifier = Modifier.size(220.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(15.dp))

            //call answer metoden
            q.options.forEach { option ->
                Button(
                    onClick = { store.answer(option) },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(option)
                }
            }

            val resultat = store.isCorrect
            if (resultat != null) {
                Spacer(modifier = Modifier.size(8.dp))

                if (resultat) {
                    Text("Riktig!")
                } else {
                    Text("FEIL, " + q.correctItem.name + " er riktig")
                }
                Spacer(modifier = Modifier.size(8.dp))


                //på knapp, call next metoden
                //og ta deg videre
                Button(onClick = {
                    store.next()
                }) {
                    Text("Neste spm.")
                }
            }

        }
    }

}