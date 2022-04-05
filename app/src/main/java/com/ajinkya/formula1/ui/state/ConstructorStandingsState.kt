package com.ajinkya.formula1.ui.state

import com.ajinkya.formula1.domain.model.Constructor
import com.ajinkya.formula1.domain.model.Schedule

sealed class ConstructorStandingsState {
    data class Loading(var isLoading: Boolean): ConstructorStandingsState()
    data class Success(var constructors: List<Constructor>): ConstructorStandingsState()
    data class Error(var error: String): ConstructorStandingsState()
}
