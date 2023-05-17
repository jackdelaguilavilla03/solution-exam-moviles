package com.u20201a963.appsuperzound.feature.Favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.u20201a963.appsuperzound.data.local.entity.AlbumEntity
import com.u20201a963.appsuperzound.navigation.BottomAppBarNavigation
import com.u20201a963.appsuperzound.ui.theme.AppSuperZoundTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favorites(navController: NavController, viewModel: FavoriteViewModel) {
    Scaffold(bottomBar = {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            content = { BottomAppBarNavigation(navController = navController) },
        )
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AppSuperZoundTheme {
                val albums by viewModel.albums.observeAsState(emptyList())
                val key = remember { mutableStateOf(0) }
                LaunchedEffect(Unit) {
                    viewModel.fetchFavorites()
                }
                LazyColumn(
                    state = LazyListState(key.value)
                ) {
                    items(albums) { album ->
                        AlbumCardFav(
                            album,
                            viewModel
                        )
                    }
                }

                LaunchedEffect(albums) {
                    key.value++
                }
            }
        }
    }
}

@Composable
fun AlbumCardFav(album: AlbumEntity, viewModel: FavoriteViewModel) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            modifier = Modifier.padding(8.dp).background(Color.Transparent),
        ) {
            AlbumImgFav(album)
            AlbumDataFav(album, viewModel)
        }
    }
}

@Composable
fun AlbumDataFav(album: AlbumEntity, viewModel: FavoriteViewModel) {
    val shouldReload = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(album.strAlbum)
            Text(album.strGenre)
        }
        IconButton(onClick = {
            viewModel.delete(album)
            shouldReload.value = true
        }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = Color.Red
            )
        }
    }
    if (shouldReload.value) {
        LaunchedEffect(Unit) {
            viewModel.fetchFavorites()
            shouldReload.value = false
        }
    }
}

@Composable
fun AlbumImgFav(album: AlbumEntity) {

    AsyncImage(
        album.strAlbum3DCase,
        contentDescription = "Album Image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(100.dp),
    )

}

