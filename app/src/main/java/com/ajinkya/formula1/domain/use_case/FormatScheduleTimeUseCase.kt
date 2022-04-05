package com.ajinkya.formula1.domain.use_case

import java.text.SimpleDateFormat
import java.util.*

class FormatScheduleTimeUseCase {

    operator fun invoke(timeStr: String): String{
        return try {
            val oldFormat = SimpleDateFormat("HH:mm:ss'Z'", Locale.ENGLISH)
            val mDate = oldFormat.parse(timeStr)
            val newFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            newFormat.format(mDate!!)
        } catch (e: Exception) {
            ""
        }
    }
}