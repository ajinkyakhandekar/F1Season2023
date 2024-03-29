package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.common.Constant

@Entity(tableName = Constant.SCHEDULE_TABLE)
data class Schedule(
    @PrimaryKey
    val round: String,
    val raceName: String,
    val country: String,
    var date: String,
    var time: String,
)
