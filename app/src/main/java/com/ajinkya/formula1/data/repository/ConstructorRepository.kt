package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.common.ResponseStatus
import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.mapper.mapConstructor
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConstructorRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getConstructors() = localDataSource.getConstructors()

    suspend fun loadConstructors() = flow {
        withContext(Dispatchers.IO){
            val remoteConstructors = remoteDataSource.getConstructors()
            localDataSource.insertConstructors(remoteConstructors.mapConstructor())
            emit(ResponseStatus.Success("Download Successful"))
        }
    }
}
