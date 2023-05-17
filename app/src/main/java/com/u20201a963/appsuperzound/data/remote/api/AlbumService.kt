package com.u20201a963.appsuperzound.data.remote.api

import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET("api/v1/json/523532/mostloved.php?format=album")
    fun fetchAlbums(): Call<AlbumResponse>
}