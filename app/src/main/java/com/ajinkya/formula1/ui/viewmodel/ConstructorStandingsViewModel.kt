package com.ajinkya.formula1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.common.ResponseStatus
import com.ajinkya.formula1.common.ifNull
import com.ajinkya.formula1.domain.use_case.GetConstructorStandingsUseCase
import com.ajinkya.formula1.ui.state.ConstructorStandingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConstructorStandingsViewModel @Inject constructor(
    private val getConstructorStandingsUseCase: GetConstructorStandingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConstructorStandingsState())
    val uiState: StateFlow<ConstructorStandingsState> = _uiState

    init {
        getConstructorStandings()
    }

    private fun getConstructorStandings() {
        getConstructorStandingsUseCase().onEach { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> {
                    _uiState.value = ConstructorStandingsState(
                        true,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Success -> {
                    _uiState.value = ConstructorStandingsState(
                        false,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Error -> {
                    _uiState.value = ConstructorStandingsState(
                        false,
                        responseStatus.data.orEmpty(),
                        responseStatus.message.ifNull(Constant.MSG_ERROR)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}