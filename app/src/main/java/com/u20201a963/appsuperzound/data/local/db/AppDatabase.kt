package com.u20201a963.appsuperzound.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.u20201a963.appsuperzound.data.local.entity.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "musizc.db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return INSTANCE as AppDatabase
        }
    }
}