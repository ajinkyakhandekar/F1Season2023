package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.mapper.toDriverEntity
import com.ajinkya.formula1.core.data.mapper.toModel
import com.ajinkya.formula1.core.database.data_source.LocalDataSource
import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.database.entity.DriverEntity
import com.ajinkya.formula1.core.network.data_source.RemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DriverRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DriverRepository {

    override fun getDrivers() = localDataSource.getDrivers().map {
        it.map(DriverEntity::toModel)
    }

    override suspend fun loadDrivers() = flow {
        val remoteDrivers = remoteDataSource.getDrivers().toDriverEntity()
        localDataSource.insertDrivers(remoteDrivers)
        emit("Download Successful")
    }.catch {
        emit("Download Failed")
    }
}