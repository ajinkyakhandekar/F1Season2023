package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.ResponseStatus
import com.ajinkya.formula1.core.data.mapper.toModel
import com.ajinkya.formula1.core.data.mapper.toScheduleEntity
import com.ajinkya.formula1.core.database.data_source.LocalDataSource
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

    override fun loadSchedule() = flow {
        emit(ResponseStatus.Loading())

        val scheduleEntity = remoteDataSource.getSchedule().toScheduleEntity()
        localDataSource.insertSchedule(scheduleEntity)

        emit(ResponseStatus.Success(scheduleEntity.map { it.toModel() }))
    }.catch {
        emit(ResponseStatus.Error(it.message.toString()))
    }
}