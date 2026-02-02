package com.example.quizapp.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quizapp.data.GalleryItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import com.example.quizapp.data.GalleryStore


@Composable
fun GalleryScreen(items: List<GalleryItem>) {
    LazyColumn {

        items(items) {
            item ->
            Row(modifier = Modifier.padding(12.dp)) {

                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.name,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.size(12.dp))
                Text(text = item.name)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}


@Composable
fun GalleryScreen(store: GalleryStore) {

    Column {

        Row {

            Button(onClick = { store.sortAZ() }) {
                Text("A-Z")
            }

            Button(onClick = { store.sortZA() }) {
                Text("Z-A")
            }

        }

        LazyColumn {

            items(store.items) {
                item ->
                Row {
                    Image(
                        painter = painterResource(id = item.imageResId),
                        contentDescription = item.name
                    )
                    Text(item.name)
                }
            }
        }
    }
}




























