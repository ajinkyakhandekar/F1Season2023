package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.common.Constant

@Entity(tableName = Constant.CONSTRUCTOR_TABLE)
data class Constructor(
    @PrimaryKey
    val position: String,
    val points: String,
    val constructorName: String,
)
