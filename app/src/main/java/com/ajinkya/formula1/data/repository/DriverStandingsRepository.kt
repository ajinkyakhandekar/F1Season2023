package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.remote.dto.mapDriver
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import com.ajinkya.formula1.domain.model.Driver
import com.ajinkya.formula1.ui.viewmodel.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DriverStandingsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun getDriverStandings(): Flow<ResponseStatus<List<Driver>>> = flow {

        emit(ResponseStatus.Loading())
        val localDriverStandings = localDataSource.getDriverStandings().map { it.mapDriver() }
        emit(ResponseStatus.Loading(localDriverStandings))

        try {
            val remoteDriverStandings = remoteDataSource.getDriverStandings().mapDriver()
            localDataSource.insertDriverStandings(remoteDriverStandings)

        } catch (e: HttpException) {
            if (localDriverStandings.isEmpty())
                emit(ResponseStatus.Error(Constant.MSG_HTTP_ERROR))
        } catch (e: IOException) {
            if (localDriverStandings.isEmpty())
                emit(ResponseStatus.Error(Constant.MSG_IO_ERROR))
        }

        val newDriverStandings = localDataSource.getDriverStandings().map { it.mapDriver() }
        emit(ResponseStatus.Success(newDriverStandings))

    }
}