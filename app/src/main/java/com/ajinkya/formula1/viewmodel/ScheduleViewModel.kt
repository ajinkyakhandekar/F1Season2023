package com.ajinkya.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.repository.Repository
import com.ajinkya.formula1.data.entity.ScheduleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val scheduleList: LiveData<ResponseStatus<ScheduleResponse>> =
        liveData(Dispatchers.IO) {
            try {
                emit(ResponseStatus.loading())
                val response = repository.getSchedule()

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
