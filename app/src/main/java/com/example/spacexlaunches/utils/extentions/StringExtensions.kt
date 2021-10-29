package com.example.spacexlaunches.utils.extentions

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

const val DATE_AT_TIME_FORMAT = "dd/MM/yyyy 'at' HH:mm"
const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

fun Long.formatDate(dateFormat: String) : String {
    val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(this), TimeZone.getDefault().toZoneId())
    val formatter = DateTimeFormatter.ofPattern(dateFormat)
    return formatter.format(localDateTime)
}

fun getDifferenceDays(dateNow: String, dateBefore: String): Long {
    val formatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.US)
    val date1 = formatter.parse(dateNow)
    val date2 = formatter.parse(dateBefore)
    val diff = date1.time - date2.time
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
}
