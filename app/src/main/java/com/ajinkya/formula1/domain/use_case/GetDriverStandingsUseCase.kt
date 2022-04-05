package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.data.repository.DriverStandingsRepository
import com.ajinkya.formula1.domain.model.Driver
import com.ajinkya.formula1.common.ResponseStatus
import kotlinx.coroutines.flow.Flow

class GetDriverStandingsUseCase(private val driverStandingsRepository: DriverStandingsRepository) {

    operator fun invoke(): Flow<ResponseStatus<List<Driver>>> {
        return driverStandingsRepository.getDriverStandings()
    }

}