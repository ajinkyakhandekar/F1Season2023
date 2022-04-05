package com.ajinkya.formula1.domain.use_case

import java.text.SimpleDateFormat
import java.util.*

class FormatScheduleDateUseCase {

    operator fun invoke(dateStr: String): String {
        return try {
            val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val mDate = oldFormat.parse(dateStr)
            val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            newFormat.format(mDate!!)
        } catch (e: Exception) {
            ""
        }
    }
}