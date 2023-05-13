package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.mapper.toConstructorEntity
import com.ajinkya.formula1.core.data.mapper.toModel
import com.ajinkya.formula1.core.database.data_source.LocalDataSource
import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.network.data_source.RemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConstructorRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ConstructorRepository {
    override fun getConstructors() = localDataSource.getConstructors().map {
        it.map(ConstructorEntity::toModel)
    }

    override suspend fun loadConstructors() = flow {
        val remoteConstructors = remoteDataSource.getConstructors()
        localDataSource.insertConstructors(remoteConstructors.toConstructorEntity())
        emit("Download Successful")
    }.catch {
        emit("Download Failed")
    }
}
