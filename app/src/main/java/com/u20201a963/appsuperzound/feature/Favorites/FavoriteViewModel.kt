package com.u20201a963.appsuperzound.feature.Favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.u20201a963.appsuperzound.data.local.db.AppDatabase
import com.u20201a963.appsuperzound.data.local.entity.AlbumEntity
import com.u20201a963.appsuperzound.data.remote.api.AlbumClient
import com.u20201a963.appsuperzound.data.repository.AlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("NAME_SHADOWING")
class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val albumDao = AppDatabase.getInstance(application).albumDao()

    private val _albums = MutableLiveData<List<AlbumEntity>>(emptyList())
    val albums : LiveData<List<AlbumEntity>> = _albums

    fun fetchFavorites() {
        val favorites = albumDao.fetchAll()
        _albums.value = favorites
    }

    fun delete(albumEntity: AlbumEntity){
       val albumEntity = AlbumEntity(albumEntity.id)
        albumDao.delete(albumEntity)
        albumEntity.isFavorite = false
    }

}