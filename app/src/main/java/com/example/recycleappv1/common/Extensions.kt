package com.example.recycleappv1.common

import android.view.View
import java.util.Calendar
import java.util.Date

fun Date.convertToCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar
}

fun String.doSomething() : String{
    return this.plus(" world")
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}