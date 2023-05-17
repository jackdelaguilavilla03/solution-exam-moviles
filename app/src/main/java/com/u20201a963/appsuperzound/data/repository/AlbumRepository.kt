package com.u20201a963.appsuperzound.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.u20201a963.appsuperzound.data.local.db.AlbumDao
import com.u20201a963.appsuperzound.data.local.entity.AlbumEntity
import com.u20201a963.appsuperzound.data.remote.api.AlbumResponse
import com.u20201a963.appsuperzound.data.remote.api.AlbumService
import com.u20201a963.appsuperzound.data.remote.model.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
) {
    private val _albums = MutableLiveData<List<Album>>(emptyList())
    val albums get() = _albums

    fun fetchAll() {
        val fetchAllCall = albumService.fetchAlbums()
        fetchAllCall.enqueue(object : Callback<AlbumResponse>{
            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {
                if (response.isSuccessful){
                    val albumResponse = response.body()
                    if (albumResponse?.loved != null){
                        _albums.value= albumResponse.loved!!
                        for (album in _albums.value!!){
                            album.isFavorite = albumDao.fetchById(album.idAlbum).isNotEmpty()
                        }
                    } else {
                        _albums.value = emptyList()
                    }
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                Log.d("AlbumRepository", t.message.toString())
            }
        })
    }

    fun insert(album: Album) {
        val albumEntity = AlbumEntity(album.idAlbum,album.strAlbum,album.strGenre, album.strAlbum3DCase)
        println(albumEntity)
        albumDao.insert(albumEntity)
        album.isFavorite = true
    }


    fun delete(album: Album){
        val albumEntity = AlbumEntity(album.idAlbum)
        albumDao.delete(albumEntity)
        album.isFavorite = false
    }

}