package com.ajinkya.formula1.common

import java.text.SimpleDateFormat
import java.util.*

fun convertTime(timeStr: String): String {
    return try {
        val oldFormat = SimpleDateFormat("HH:mm:ss'Z'", Locale.ENGLISH)
        val mDate = oldFormat.parse(timeStr)
        val newFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        newFormat.format(mDate!!)
    } catch (e: Exception) {
        ""
    }
}

fun convertDate(dateStr: String): String {
    return try {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val mDate = oldFormat.parse(dateStr)
        val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        newFormat.format(mDate!!)
    } catch (e: Exception) {
        ""
    }
}