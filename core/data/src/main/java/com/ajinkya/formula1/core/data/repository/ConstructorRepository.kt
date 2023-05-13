package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.model.Constructor
import kotlinx.coroutines.flow.Flow

interface ConstructorRepository {
    fun getConstructors(): Flow<List<Constructor>>
    suspend fun loadConstructors(): Flow<String>
}
