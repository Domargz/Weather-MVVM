package com.david.weathermvvm.di.modules

import com.david.weathermvvm.model.datasource.db.DataBaseRepository
import com.david.weathermvvm.model.datasource.db.room.DataBaseRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryDataBaseModule {

    @Binds
    abstract fun bindDataBaseRepository(dataBaseRepositoryImp: DataBaseRepositoryImp): DataBaseRepository

}