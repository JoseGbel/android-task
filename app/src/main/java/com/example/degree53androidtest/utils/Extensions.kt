package com.example.degree53androidtest.utils

import kotlin.math.round

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