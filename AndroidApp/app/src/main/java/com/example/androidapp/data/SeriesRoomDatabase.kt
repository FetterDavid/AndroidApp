package com.example.androidapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Series::class],
    version =1,
    exportSchema = false
)
abstract class SeriesRoomDatabase: RoomDatabase() {
    abstract fun seriesDao(): SeriesDao

    companion object {
        @Volatile
        private var INSTANCE: SeriesRoomDatabase? = null

        fun getDatabase(context: Context): SeriesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SeriesRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}