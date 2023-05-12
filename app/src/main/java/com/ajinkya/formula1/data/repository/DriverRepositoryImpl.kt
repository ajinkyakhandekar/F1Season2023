package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.mapper.mapDriver
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DriverRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DriverRepository {

    override fun getDrivers() = localDataSource.getDrivers()

    override suspend fun loadDrivers() = flow {
        val remoteDrivers = remoteDataSource.getDrivers().mapDriver()
        localDataSource.insertDrivers(remoteDrivers)
        emit("Download Successful")
    }.catch {
        emit("Download Failed")
    }
}