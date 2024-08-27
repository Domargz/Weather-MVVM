package com.david.weathermvvm.modules

import android.content.Context
import androidx.room.Room
import com.david.weathermvvm.model.datasource.db.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    @Provides
    @Named("test_db")
    fun provideCitiesDao(database: AppDatabase) = database.citiesDao()

}