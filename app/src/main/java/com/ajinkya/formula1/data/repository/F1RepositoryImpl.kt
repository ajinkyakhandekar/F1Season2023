package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.local.repository.LocalRepository
import com.ajinkya.formula1.data.remote.repository.RemoteRepository
import com.ajinkya.formula1.domain.model.Schedule
import com.ajinkya.formula1.ui.viewmodel.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class F1RepositoryImpl(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : F1Repository {

    override fun geSchedule(): Flow<ResponseStatus<List<Schedule>>> = flow {

        emit(ResponseStatus.Loading())
        val localSchedule = localRepository.getSchedule().map { it.mapSchedule() }
        emit(ResponseStatus.Loading(localSchedule))

        try {
            val remoteSchedule = remoteRepository.getSchedule().mapSchedule()
            localRepository.insertSchedule(remoteSchedule)

        } catch (e: HttpException) {
            if(localSchedule.isNotEmpty())
            emit(ResponseStatus.Error("Oops, something went wrong!"))
        } catch (e: IOException) {
            if(localSchedule.isNotEmpty())
            emit(ResponseStatus.Error("Couldn't reach server, check your internet connection."))
        }

        val newLocalSchedule = localRepository.getSchedule().map { it.mapSchedule() }
        emit(ResponseStatus.Success(newLocalSchedule))

    }
}