package com.example.testhero.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, top = 40.dp)

                ){
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Back",
                        modifier = Modifier
                                .size(50.dp),

                    )
                    Column {
                        Text(text = "User")


                        Text(text = "User")
                    }
                    Spacer(Modifier.padding(start = 250.dp))
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(50.dp)
                        )
                }
                LazyColumn {
                    if (posts != null) {
                        items(posts!!.size) { index ->
                            val post = posts!![index]
                            PostItem(post = post, navController = navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PostItem(post: Superhero, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { navController.navigate("HeroDetail/${post.id}") }
    )  {
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


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HeroDetail(hero: Superhero, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(hero.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = hero.images.lg,
                contentDescription = "Hero Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                Modifier

            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {

                    Text("Full Name: ${hero.biography.fullName}", style = MaterialTheme.typography.titleMedium)
                    Text("Aliases: ${hero.biography.aliases.joinToString()}")
                    Text("Publisher: ${hero.biography.publisher}")
                    Text("Alignment: ${hero.biography.alignment}")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Power Stats", style = MaterialTheme.typography.titleMedium)
                    Text("Intelligence: ${hero.powerstats.intelligence}")
                    Text("Strength: ${hero.powerstats.strength}")
                    Text("Speed: ${hero.powerstats.speed}")
                    Text("Durability: ${hero.powerstats.durability}")
                    Text("Power: ${hero.powerstats.power}")
                    Text("Combat: ${hero.powerstats.combat}")
                }
            }


        }
    }
}