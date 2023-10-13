package com.example.recycleappv1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.recycleappv1.ui.reminder.notification.channelID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoreApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        val channelName: CharSequence = " Non Burnable Reminder"
        val importance = NotificationManager.IMPORTANCE_HIGH

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, channelName, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelBurnable: CharSequence = " Burnable Reminder"
        val importance2 = NotificationManager.IMPORTANCE_HIGH

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, channelBurnable, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}