package com.david.weathermvvm.model.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cities::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun citiesDao(): CitiesDao
}