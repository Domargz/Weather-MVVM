package com.david.weathermvvm.di.modules

import android.content.Context
import androidx.room.Room
import com.david.weathermvvm.model.datasource.db.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "city_database").build()

    @Provides
    fun provideCitiesDao(db: AppDatabase) = db.citiesDao()

}