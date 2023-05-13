package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.core.common.Constant

@Entity(tableName = com.ajinkya.formula1.core.common.Constant.CONSTRUCTOR_TABLE)
data class Constructor(
    @PrimaryKey
    val position: String,
    val points: String,
    val constructorName: String,
)
