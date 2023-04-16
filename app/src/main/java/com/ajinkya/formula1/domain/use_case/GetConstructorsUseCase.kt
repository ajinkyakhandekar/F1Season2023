package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.data.repository.ConstructorRepository
import com.ajinkya.formula1.data.local.entity.Constructor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConstructorsUseCase @Inject constructor(
    private val constructorRepository: ConstructorRepository
) {
    operator fun invoke(): Flow<List<Constructor>> {
        return constructorRepository.getConstructors()
    }
}
