package com.u20201a963.appsuperzound.feature.ListAlbum

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.u20201a963.appsuperzound.data.remote.model.Album
import com.u20201a963.appsuperzound.navigation.BottomAppBarNavigation
import com.u20201a963.appsuperzound.ui.theme.AppSuperZoundTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAlbum(navController: NavController, viewModel: ListAlbumViewModel) {
    Scaffold(bottomBar = {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            content = { BottomAppBarNavigation(navController = navController) })
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xfff2f2f2)) {
            AppSuperZoundTheme {
                AlbumList(viewModel)
            }
        }
    }
}

@Composable
fun AlbumList(viewModel: ListAlbumViewModel) {
    val albums by viewModel.albums.observeAsState(listOf())
    val key = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit){
        viewModel.fetchAlbums()
    }

    LazyColumn {
        items(albums) { album ->
            AlbumCard(
                album,
                insertAlbum = { viewModel.insert(album) },
                deleteAlbum = { viewModel.delete(album) })
        }
    }

    LaunchedEffect(albums){
        key.value++
    }
}

@Composable
fun AlbumCard(
    album: Album,
    insertAlbum: () -> Unit,
    deleteAlbum: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = RoundedCornerShape(1.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AlbumImg(album)
            AlbumData(album, insertAlbum, deleteAlbum)
        }
    }
}

@Composable
fun AlbumData(
    album: Album,
    insertAlbum: () -> Unit,
    deleteAlbum: () -> Unit
) {
    val isFav = remember { mutableStateOf(false) }
    isFav.value = album.isFavorite
    Spacer(modifier = Modifier.padding(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(album.strAlbum, style = MaterialTheme.typography.bodyMedium)
            Text(album.strArtist, style = MaterialTheme.typography.titleSmall)
            Text(album.intYearReleased, style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = {
            if (isFav.value) {
                deleteAlbum()
            }
            else {
                insertAlbum()
            }
            isFav.value = !isFav.value
        }) {
            Icon(
                Icons.Filled.Favorite, contentDescription = null,
                tint = if (isFav.value) Color.Red else Color.Gray
            )
        }
    }
}

@Composable
fun AlbumImg(album: Album) {
    AsyncImage(
        model = album.strAlbumThumb, contentDescription = null,
        modifier = Modifier.size(100.dp), contentScale = ContentScale.Crop
    )
}
