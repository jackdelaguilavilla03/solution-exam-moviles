package com.u20201a963.appsuperzound.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumClient {
    private const val BASE_URL = "https://theaudiodb.com/"

    fun albumService(): AlbumService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AlbumService::class.java)
    }
}