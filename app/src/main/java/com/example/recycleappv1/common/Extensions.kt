package com.example.recycleappv1.common

import android.view.View
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


fun Date.convertToCalendarWithoutTime(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar
}
fun Date.convertToCalendarWithTime(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    println("Time before: ${calendar.timeInMillis} ${calendar.timeZone.displayName}")
    return calendar
}
fun Date.dateToDayOfWeek(): String {
    val calendar = this.convertToCalendarWithoutTime()
    calendar.time = this
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    // Convert the day of the week integer to a string
    val daysOfWeek = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    return daysOfWeek[dayOfWeek - 1]
}

fun Date.convertToReadable(): String {
    val spf = SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa")
    return spf.format(this)
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


fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

