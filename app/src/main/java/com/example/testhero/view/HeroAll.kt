package com.example.testhero.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.testhero.model.Superhero
import com.example.testhero.viewmodel.MainViewModel

@Composable
fun HeroAll(navController: NavController, viewModel: MainViewModel = viewModel()){
    val posts by viewModel.heros.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()



    Box{
        Column {
            if (errorMessage != null ){
                Text(
                    text = "Error: ${errorMessage!!}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 30.dp)
                )
            } else {
                LazyColumn {
                    if (posts != null) {
                        items(posts!!.size) { index ->
                            val post = posts!![index]
                            PostItem(post = post)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PostItem(post: Superhero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = post.images.md,
                contentDescription = "Hero Image",
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Name: ${post.name}", style = MaterialTheme.typography.titleSmall)
                Text(text = "Slug: ${post.slug}", style = MaterialTheme.typography.titleSmall)
            }

        }
    }
}