package com.ajinkya.formula1.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.core.common.Constant

@Entity(tableName = Constant.SCHEDULE_TABLE)
data class ScheduleEntity(
    @PrimaryKey
    val round: String,
    val raceName: String,
    val country: String,
    var date: String,
    var time: String,
)
