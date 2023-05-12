package com.ajinkya.formula1.data.di

import com.ajinkya.formula1.data.repository.ConstructorRepository
import com.ajinkya.formula1.data.repository.ConstructorRepositoryImpl
import com.ajinkya.formula1.data.repository.DriverRepository
import com.ajinkya.formula1.data.repository.DriverRepositoryImpl
import com.ajinkya.formula1.data.repository.ScheduleRepository
import com.ajinkya.formula1.data.repository.ScheduleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsScheduleRepository(
        scheduleRepository: ScheduleRepositoryImpl,
    ): ScheduleRepository

    @Binds
    fun bindsDriverRepository(
        driverRepository: DriverRepositoryImpl,
    ): DriverRepository

    @Binds
    fun bindsConstructorRepository(
        constructorRepository: ConstructorRepositoryImpl,
    ): ConstructorRepository
}
