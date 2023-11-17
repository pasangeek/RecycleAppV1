package com.example.recycleappv1.common

import java.util.Calendar

object Utils {
    /**
     * Returns a Calendar instance representing the current date with time components set to zero.
     * @return Calendar instance with time set to midnight (00:00:00.000).
     */

    fun getCurrentDate(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar;
    }

}