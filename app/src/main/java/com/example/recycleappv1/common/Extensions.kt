package com.example.recycleappv1.common

import android.view.View
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * Converts a Date object to a Calendar object without considering the time.
 * Sets the time fields (hour, minute, second, millisecond) to zero.
 */
fun Date.convertToCalendarWithoutTime(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar
}/**
 * Converts a Date object to a Calendar object, preserving the time information.
 */
fun Date.convertToCalendarWithTime(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    println("Time before: ${calendar.timeInMillis} ${calendar.timeZone.displayName}")
    return calendar
}/**
 * Returns the day of the week for a given Date as a String (e.g., "Monday", "Tuesday").
 */
fun Date.dateToDayOfWeek(): String {
    val calendar = this.convertToCalendarWithoutTime()
    calendar.time = this
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    // Convert the day of the week integer to a string
    val daysOfWeek = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    return daysOfWeek[dayOfWeek - 1]
}
/**
 * Converts a Date object to a readable string format using the pattern "MMM dd, yyyy" (e.g., "Jan 01, 2023").
 */
fun Date.convertToReadable(): String {
    val spf = SimpleDateFormat("MMM dd, yyyy")
    return spf.format(this)
}/**
 * Sets the visibility of a View to VISIBLE.
 */
fun View.show() {
    visibility = View.VISIBLE
}
/**
 * Sets the visibility of a View to GONE.
 */
fun View.gone() {
    visibility = View.GONE
}
/**
 * Sets the visibility of a View to INVISIBLE.
 */
fun View.hide() {
    visibility = View.INVISIBLE
}
/**
 * Enables interaction with a View by setting its isEnabled property to true.
 */

fun View.enable() {
    isEnabled = true
}
/**
 * Disables interaction with a View by setting its isEnabled property to false.
 */
fun View.disable() {
    isEnabled = false
}

