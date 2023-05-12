package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.local.entity.Constructor
import kotlinx.coroutines.flow.Flow

interface ConstructorRepository {
    fun getConstructors(): Flow<List<Constructor>>
    suspend fun loadConstructors(): Flow<String>
}
