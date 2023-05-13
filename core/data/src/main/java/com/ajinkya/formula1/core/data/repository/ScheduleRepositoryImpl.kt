package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.mapper.toModel
import com.ajinkya.formula1.core.data.mapper.toScheduleEntity
import com.ajinkya.formula1.core.database.data_source.LocalDataSource
import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.database.entity.ScheduleEntity
import com.ajinkya.formula1.core.network.data_source.RemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ScheduleRepository {

    override fun getSchedule() = localDataSource.getSchedule().map {
        it.map(ScheduleEntity::toModel)
    }

    override suspend fun loadSchedule() = flow {
        val remoteSchedule = remoteDataSource.getSchedule().toScheduleEntity()
        localDataSource.insertSchedule(remoteSchedule)
        emit("Download Successful")
    }.catch {
        emit("Download Failed")
    }
}