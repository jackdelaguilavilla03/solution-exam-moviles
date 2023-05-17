package com.u20201a963.appsuperzound.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
class AlbumEntity(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "strAlbum")
    @JvmField
    val strAlbum: String,

    @ColumnInfo(name = "strGenre")
    @JvmField
    val strGenre: String,

    @ColumnInfo(name = "strAlbum3DCase")
    @JvmField
    val strAlbum3DCase: String,
) {
    var isFavorite: Boolean = false

    constructor(id: String) : this(id, "", "", "")
}