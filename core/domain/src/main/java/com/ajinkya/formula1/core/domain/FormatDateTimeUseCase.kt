package com.ajinkya.formula1.core.domain

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FormatDateTimeUseCase @Inject constructor() {

    operator fun invoke(dateStr: String, currentPattern: String, newPattern: String): String? {
        return try {
            val currentFormat = SimpleDateFormat(currentPattern, Locale.UK)
            val mDate = currentFormat.parse(dateStr)
            val newFormat = SimpleDateFormat(newPattern, Locale.UK)
            mDate?.let { newFormat.format(it) }
        } catch (e: ParseException) {
            null
        }
    }
}
