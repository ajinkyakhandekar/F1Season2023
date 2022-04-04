package com.ajinkya.formula1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.remote.dto.StandingsResponse
import com.ajinkya.formula1.data.remote.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(private val remoteRepository: RemoteRepository) : ViewModel() {

    val driverStandingsList: LiveData<ResponseStatus<StandingsResponse>> =
        liveData(Dispatchers.IO) {
            try {
                emit(ResponseStatus.loading())
                val response = remoteRepository.getDriverStandings()

                if (response.isSuccessful) {
                    emit(ResponseStatus.success(response.body()))
                } else {
                    emit(ResponseStatus.error(Constant.MSG_ERROR))
                }

            } catch (e: Exception) {
                emit(ResponseStatus.error(Constant.MSG_ERROR))
            }
        }

    val constructorStandingsList: LiveData<ResponseStatus<StandingsResponse>> =
        liveData(Dispatchers.IO) {
            try {
                emit(ResponseStatus.loading())
                val response = remoteRepository.getConstructorStandings()

                if (response.isSuccessful) {
                    emit(ResponseStatus.success(response.body()))
                } else {
                    emit(ResponseStatus.error(Constant.MSG_ERROR))
                }

            } catch (e: Exception) {
                emit(ResponseStatus.error(Constant.MSG_ERROR))
            }
        }
}