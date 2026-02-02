package com.example.quizapp.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.quizapp.data.GalleryStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(store: GalleryStore) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gallery") }
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {


            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = { store.sortAZ() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("A-Z")
                }

                Spacer(modifier = Modifier.size(8.dp))

                Button(
                    onClick = { store.sortZA() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Z-A")
                }
            }

            // Liste med bilder
            LazyColumn {
                items(store.items) { item ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {

                        Image(
                            painter = painterResource(id = item.imageResId),
                            contentDescription = item.name,
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.size(12.dp))

                        Text(
                            text = item.name,
                            modifier = Modifier
                                .weight(1f)
                                .alignByBaseline()
                        )

                        Spacer(modifier = Modifier.size(12.dp))

                        Button(onClick = { store.remove(item) }) {
                            Text("Slett")
                        }
                    }
                }
            }
        }
    }
}