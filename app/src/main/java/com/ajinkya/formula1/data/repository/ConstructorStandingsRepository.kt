package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.remote.dto.mapConstructor
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import com.ajinkya.formula1.domain.model.Constructor
import com.ajinkya.formula1.ui.viewmodel.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ConstructorStandingsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getConstructorStandings(): Flow<ResponseStatus<List<Constructor>>> = flow {

        emit(ResponseStatus.Loading())
        val localConstructorStandings = localDataSource.getConstructorStandings().map { it.mapConstructor() }
        emit(ResponseStatus.Loading(localConstructorStandings))

        try {
            val remoteConstructorStandings = remoteDataSource.getConstructorStandings().mapConstructor()
            localDataSource.insertConstructorStandings(remoteConstructorStandings)

        } catch (e: HttpException) {
            if (localConstructorStandings.isEmpty())
                emit(ResponseStatus.Error(Constant.MSG_HTTP_ERROR))
        } catch (e: IOException) {
            if (localConstructorStandings.isEmpty())
                emit(ResponseStatus.Error(Constant.MSG_IO_ERROR))
        }

        val newLocalConstructorStandings = localDataSource.getConstructorStandings().map { it.mapConstructor() }
        emit(ResponseStatus.Success(newLocalConstructorStandings))

    }
}