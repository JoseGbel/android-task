package com.example.degree53androidtest.utils

import android.util.TimeUtils
import java.sql.Time
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.round
import kotlin.collections.get as get1

/**
 * This method converts an integer such as "12345" to the string "1.2k"
 */
fun Int.kRoundify() : String{
    var multiplier = 10

    return if (this/1000 >= 1) {
        val roundedNumber = round((this * multiplier / 1000).toDouble())
        (roundedNumber/multiplier).toString()+"k"
    } else {
        this.toString()
    }
}

fun String.convertDate() : String {
    lateinit var day : String
    lateinit var month : String
    lateinit var year : String

    val monthNames = HashMap<Int, String>()
    monthNames[1]  = "Jan"
    monthNames[2]  = "Feb"
    monthNames[3]  = "Mar"
    monthNames[4]  = "Apr"
    monthNames[5]  = "May"
    monthNames[6]  = "Jun"
    monthNames[7]  = "Jul"
    monthNames[8]  = "Aug"
    monthNames[9]  = "Obt"
    monthNames[10] = "Sep"
    monthNames[11] = "Nov"
    monthNames[12] = "Dec"

    val parts = this.split("-", "T")
    year = parts[0]
    month = monthNames[parts[1].toInt()]!!
    day = parts[2]

    if (year.toInt() < Calendar.getInstance().get(Calendar.YEAR))
        return "$day $month $year"

    return "$day $month"
}