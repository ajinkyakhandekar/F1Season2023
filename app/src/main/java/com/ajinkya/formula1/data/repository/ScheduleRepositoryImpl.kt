package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.mapper.mapSchedule
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ScheduleRepository {

    override fun getSchedule() = localDataSource.getSchedule()

    override suspend fun loadSchedule() = flow {
        val remoteSchedule = remoteDataSource.getSchedule().mapSchedule()
        localDataSource.insertSchedule(remoteSchedule)
        emit("Download Successful")
    }.catch {
        emit("Download Failed")
    }
}