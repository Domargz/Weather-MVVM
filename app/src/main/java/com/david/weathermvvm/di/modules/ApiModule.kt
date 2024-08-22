package com.david.weathermvvm.di.modules

import com.david.weathermvvm.model.repository.apiclient.ApiImplementation
import com.david.weathermvvm.model.repository.apiclient.Request
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    abstract fun bindApi(apiImplementation: ApiImplementation): Request

}