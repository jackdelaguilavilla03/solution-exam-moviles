package com.u20201a963.appsuperzound.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.u20201a963.appsuperzound.data.local.entity.AlbumEntity

@Dao
interface AlbumDao {
    @Query("select * from Album where id=:id")
    fun fetchById(id: String): List<AlbumEntity>

    @Query("select * from Album where strAlbum=:strAlbum")
    fun fetchByAlbum(strAlbum: String): List<AlbumEntity>

    @Query("select * from Album")
    fun fetchAll(): List<AlbumEntity>

    @Insert
    fun insert(albumEntity: AlbumEntity)

    @Delete
    fun delete(albumEntity: AlbumEntity)
}