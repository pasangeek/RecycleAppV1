package com.example.recycleappv1.common

import java.util.Calendar

object Utils {
    fun getCurrentDate(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar;
    }

}