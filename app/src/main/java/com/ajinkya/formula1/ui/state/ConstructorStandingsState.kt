package com.ajinkya.formula1.ui.state

import com.ajinkya.formula1.domain.model.Constructor

data class ConstructorStandingsState(
    var isLoading: Boolean = false,
    var constructorList: List<Constructor> = emptyList(),
    var errorMessage: String = ""
)
