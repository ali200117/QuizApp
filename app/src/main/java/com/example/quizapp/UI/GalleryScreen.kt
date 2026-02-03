package com.example.quizapp.UI

import android.content.Intent
import android.icu.text.CaseMap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.AsyncImage
import com.example.quizapp.data.GalleryStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(store: GalleryStore) {

    //Compose != direkte tilgang -> API, må ha context først
    val context = LocalContext.current
//Launcher image velger
    val imagePickerLaunch =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) {
            uri ->
            if (uri != null) {
               context.contentResolver.takePersistableUriPermission(
                   uri,
                   Intent.FLAG_GRANT_READ_URI_PERMISSION
               )
                //addFromGallery->AddButton
                store.addFromGallery(uri)
            }
        }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gallery") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {imagePickerLaunch.launch(arrayOf("image/*"))
            }
            )
            { Text("Add")}
        },
        floatingActionButtonPosition = FabPosition.End

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
            //tekst alert boks ved valg av bilde
            if(store.newImageUri != null){

                //først tom, kalle den når bruker skriver
                var textAnswer by remember { mutableStateOf("") }
                AlertDialog(
                    //hvis du skrur mobil av/etc..
                    onDismissRequest = {
                        store.dismissImage()
                    },
                    confirmButton = {
                        Button(onClick = {
                            store.addTextImage(textAnswer)
                        }) {
                            Text("confirm")
                        }
                    },
                    //cancle dialog & bilde
                    dismissButton = {
                        Button(onClick = {store.dismissImage()}) {
                            Text("cancle")
                        }
                    },
                    //bruker skriver tekst
                    text = {
                        TextField(
                            value = textAnswer,
                            onValueChange = {
                                textAnswer = it
                            }
                        )
                    },
                    title = {},
                )
            }

            // Liste med bilder
            LazyColumn {
                items(store.items) { item ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {


                        when{
                            //bilder fra cameraRoll
                            item.imageUri != null -> {
                                AsyncImage(
                                    model = item.imageUri,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(80.dp)
                                )
                            }
                                //hard kodet bilder
                            item.imageResId != null -> {

                                Image(
                                    painter = painterResource(id = item.imageResId),
                                    contentDescription = item.name,
                                    modifier = Modifier.size(80.dp)
                                )
                            }
                        }

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