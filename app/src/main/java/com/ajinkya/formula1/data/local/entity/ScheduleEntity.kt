package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.domain.model.Schedule

@Entity(tableName = Constant.SCHEDULE_TABLE)
data class ScheduleEntity(
    @PrimaryKey
    var round: String = "",
    var raceName: String = "",
    var country: String = "",
    var date: String = "",
    var time: String = ""
) {
    fun mapSchedule(): Schedule {
        return Schedule(
            round = round,
            raceName = raceName,
            country = country,
            date = date,
            time = time
        )
    }
}
