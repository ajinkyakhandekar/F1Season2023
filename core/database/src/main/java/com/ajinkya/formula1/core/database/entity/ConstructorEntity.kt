package com.ajinkya.formula1.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.core.common.Constant

@Entity(tableName = Constant.CONSTRUCTOR_TABLE)
data class ConstructorEntity(
    @PrimaryKey
    val position: String,
    val points: String,
    val constructorName: String,
)
