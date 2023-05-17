package com.u20201a963.appsuperzound.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.u20201a963.appsuperzound.feature.Favorites.FavoriteViewModel
import com.u20201a963.appsuperzound.feature.Favorites.Favorites
import com.u20201a963.appsuperzound.feature.Home.Home
import com.u20201a963.appsuperzound.feature.ListAlbum.ListAlbum
import com.u20201a963.appsuperzound.feature.ListAlbum.ListAlbumViewModel

@Composable
fun AppNavigation(listAlbumViewModel: ListAlbumViewModel, favoriteViewModel: FavoriteViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home" ){
        composable( "Home" ){
            Home(navController)
        }
        composable( "Albums" ){
            ListAlbum(navController,listAlbumViewModel)
        }
        composable("Favorites" ){
            Favorites(navController, favoriteViewModel)
        }
    }
}