package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.data.repository.DriverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDriversUseCase @Inject constructor(
    private val driverRepository: DriverRepository
) {
    operator fun invoke(): Flow<List<Driver>> {
        return driverRepository.getDrivers()
    }
}
