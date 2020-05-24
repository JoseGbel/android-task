package com.example.degree53androidtest.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.round

/**
 * Converts an integer such as "12345" to the string "1.2k"
 */
fun Int.kRoundify() : String{
    val multiplier = 10

    return if (this/1000 >= 1) {
        val roundedNumber = round((this * multiplier / 1000).toDouble())
        (roundedNumber/multiplier).toString()+"k"
    } else {
        this.toString()
    }
}


/**
 * Converts the string timestamp from github in ISO 8601 format 'YYYY-MM-DDTHH:MM:SSZ'
 * to 'DD Mth YYYY' (23 May 2020)
 */
fun String.dateConvert() : String {
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

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}