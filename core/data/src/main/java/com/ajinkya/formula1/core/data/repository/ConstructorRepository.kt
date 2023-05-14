package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.ResponseStatus
import com.ajinkya.formula1.core.model.Constructor
import kotlinx.coroutines.flow.Flow

interface ConstructorRepository {
    fun getConstructors(): Flow<List<Constructor>>
    fun loadConstructors(): Flow<ResponseStatus<List<Constructor>>>
}
