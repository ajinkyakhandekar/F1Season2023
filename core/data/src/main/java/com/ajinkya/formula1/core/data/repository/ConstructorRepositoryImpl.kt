package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.ResponseStatus
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

    override fun loadConstructors() = flow {
        emit(ResponseStatus.Loading())

        val constructorEntity = remoteDataSource.getConstructors().toConstructorEntity()
        localDataSource.insertConstructors(constructorEntity)

        emit(ResponseStatus.Success(constructorEntity.map { it.toModel() }))
    }.catch {
        emit(ResponseStatus.Error(it.message.toString()))
    }
}
