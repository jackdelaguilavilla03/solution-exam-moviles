package com.u20201a963.appsuperzound.feature.ListAlbum

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.u20201a963.appsuperzound.data.local.db.AppDatabase
import com.u20201a963.appsuperzound.data.remote.api.AlbumClient
import com.u20201a963.appsuperzound.data.remote.model.Album
import com.u20201a963.appsuperzound.data.repository.AlbumRepository

class ListAlbumViewModel(application: Application): AndroidViewModel(application) {
    private val albumService = AlbumClient.albumService()
    private val albumDao = AppDatabase.getInstance(application).albumDao()
    private val albumRepository = AlbumRepository(albumService, albumDao)

    private var _albums = albumRepository.albums
    val albums get() = _albums

    fun fetchAlbums() {
        albumRepository.fetchAll()
        _albums.value = albumRepository.albums.value
    }

    fun insert(album: Album){
        albumRepository.insert(album)
    }

    fun delete(album: Album){
        albumRepository.delete(album)
    }

}