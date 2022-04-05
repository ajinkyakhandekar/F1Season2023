package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.common.ifEmpty
import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.remote.dto.mapSchedule
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import com.ajinkya.formula1.domain.model.Schedule
import com.ajinkya.formula1.common.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ScheduleRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getSchedule(): Flow<ResponseStatus<List<Schedule>>> = flow {

        emit(ResponseStatus.Loading())
        val localSchedule = localDataSource.getSchedule().map { it.mapSchedule() }
        emit(ResponseStatus.Loading(localSchedule))

        try {
            val remoteSchedule = remoteDataSource.getSchedule().mapSchedule()
            localDataSource.insertSchedule(remoteSchedule)

        } catch (e: HttpException) {
            localSchedule.ifEmpty {
                emit(ResponseStatus.Error(Constant.MSG_HTTP_ERROR))
            }
        } catch (e: IOException) {
            localSchedule.ifEmpty {
                emit(ResponseStatus.Error(Constant.MSG_IO_ERROR))
            }
        }

        val newLocalSchedule = localDataSource.getSchedule().map { it.mapSchedule() }
        emit(ResponseStatus.Success(newLocalSchedule))

    }
}