package com.ajinkya.formula1.core.common

object Constant {
    const val TAG = "F1_LOGS"

    const val BASE_URL = "https://ergast.com/api/f1/"
    private const val SEASON = "2023"
    const val SCHEDULE = "$SEASON.json"
    const val STANDINGS_DRIVER = "$SEASON/driverStandings.json"
    const val STANDINGS_CONSTRUCTOR = "$SEASON/constructorStandings.json"

    const val F1_DATABASE = "F1_DATABASE"
    const val SCHEDULE_TABLE = "SCHEDULE_TABLE"
    const val DRIVER_TABLE = "DRIVER_TABLE"
    const val CONSTRUCTOR_TABLE = "CONSTRUCTOR_TABLE"

    const val MSG_ERROR = "Api Error"
    const val MSG_HTTP_ERROR = "Something went wrong! Please try again"
    const val MSG_IO_ERROR = "Couldn't reach server, check your internet connection"

    const val REMOTE_DATE_FORMAT = "yyyy-MM-dd"
    const val REMOTE_TIME_FORMAT = "HH:mm:ss'Z'"

    const val DISPLAY_DATE_FORMAT = "dd/MM/yyyy"
    const val DISPLAY_TIME_FORMAT = "HH:mm"
}