package com.ajinkya.formula1.di

import com.ajinkya.formula1.data.repository.Repository
import com.ajinkya.formula1.data.repository.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getDataSource(dataRepository: DataRepository): Repository

}