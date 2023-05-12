package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.mapper.mapConstructor
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConstructorRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ConstructorRepository {
    override fun getConstructors() = localDataSource.getConstructors()

    override suspend fun loadConstructors() = flow {
        val remoteConstructors = remoteDataSource.getConstructors()
        localDataSource.insertConstructors(remoteConstructors.mapConstructor())
        emit("Download Successful")
    }.catch {
        emit("Download Failed")
    }
}
