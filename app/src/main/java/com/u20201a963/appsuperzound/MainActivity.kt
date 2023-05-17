package com.u20201a963.appsuperzound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.u20201a963.appsuperzound.feature.Favorites.FavoriteViewModel
import com.u20201a963.appsuperzound.feature.ListAlbum.ListAlbumViewModel
import com.u20201a963.appsuperzound.navigation.AppNavigation
import com.u20201a963.appsuperzound.ui.theme.AppSuperZoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSuperZoundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = applicationContext
                    val listAlbumViewModel : ListAlbumViewModel by viewModels()
                    val favoriteViewModel: FavoriteViewModel by viewModels()
                    AppNavigation(listAlbumViewModel, favoriteViewModel)
                }
            }
        }
    }
}