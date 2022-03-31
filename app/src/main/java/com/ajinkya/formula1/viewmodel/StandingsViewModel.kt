package com.ajinkya.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.entity.StandingsResponse
import com.ajinkya.formula1.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val driverStandingsList: LiveData<ResponseStatus<StandingsResponse>> =
        liveData(Dispatchers.IO) {
            try {
                emit(ResponseStatus.loading())
                val response = repository.getDriverStandings()

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
                val response = repository.getConstructorStandings()

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