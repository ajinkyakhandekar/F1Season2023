package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.data.repository.ConstructorStandingsRepository
import com.ajinkya.formula1.domain.model.Constructor
import com.ajinkya.formula1.ui.viewmodel.ResponseStatus
import kotlinx.coroutines.flow.Flow

class GetConstructorStandingsUseCase(
    private val constructorStandingsRepository: ConstructorStandingsRepository
) {

    operator fun invoke(): Flow<ResponseStatus<List<Constructor>>> {
        return constructorStandingsRepository.getConstructorStandings()
    }

}